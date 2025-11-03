package booktracker.service.book;

import booktracker.service.author.AuthorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import booktracker.dao.book.BookDao;
import booktracker.entities.Author;
import booktracker.entities.Book;
import booktracker.exceptions.BookNotFoundException;

import static booktracker.service.book.logs.BookCrudLogMessages.*;

public class BookCrudService {

    private final BookDao bookDao;
    private final AuthorService authorService;
    private static final Logger logger = LogManager.getLogger(BookCrudService.class.getName());

    public BookCrudService(BookDao bookDao, AuthorService authorService) {
        this.bookDao = bookDao;
        this.authorService = authorService;
    }

    public void save(Book book) {
        if (bookDao.bookExistsByTitle(book.getTitle())) {
            logger.warn(SAVE_FAIL_DUPLICATED_TITLE_LOG, book.getTitle());
            throw new IllegalArgumentException("Book with that title already exists.");
        }

        Author author = authorService.findByName(book.getAuthor().getName());
        if (author == null) {
            author = authorService.save(book.getAuthor());
        }
        book.setAuthor(author);

        bookDao.insert(book);
        logger.info(SAVE_SUCCESS_LOG, book.getTitle());
    }

    public void update(ObjectId id, Book updatedBook) {
        if (bookDao.findBookById(id) == null) {
            logger.warn(UPDATE_FAIL_LOG, updatedBook.getTitle());
            throw new BookNotFoundException("Book not found with ID: " + id);
        }
        bookDao.update(id, updatedBook);
        logger.info(UPDATE_SUCCESS_LOG, updatedBook.getTitle());
    }

    public void delete(ObjectId id) {
        if (bookDao.findBookById(id) == null) {
            logger.warn(DELETE_FAIL_LOG, id.toHexString());
            throw new BookNotFoundException("Book not found with ID: " + id);
        }
        bookDao.delete(id);
        logger.info(DELETE_SUCCESS_LOG, id.toHexString());
    }

}