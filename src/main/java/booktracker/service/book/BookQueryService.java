package booktracker.service.book;

import booktracker.exceptions.AuthorNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import booktracker.dao.book.BookDao;
import booktracker.entities.Book;
import booktracker.exceptions.BookNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static booktracker.log.BookQueryLogMessages.*;

public class BookQueryService {

    private final BookDao bookDao;
    private static final Logger logger = LogManager.getLogger(BookQueryService.class.getName());

    public BookQueryService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<Book> findAll() {
        return bookDao.findAllBooks();
    }

    public Book findById(ObjectId id) {
        Book book = bookDao.findBookById(id);

        if (book == null) {
            logger.warn(FIND_BY_ID_FAIL_LOG, id.toHexString());
            throw new BookNotFoundException("Book not found with ID: " + id);
        }

        logger.info(FIND_BY_ID_SUCCESS_LOG, id.toHexString());
        return book;
    }

    public Book findByTitle(String title) {
        Book book = bookDao.findBookByTitle(title);

        if (book == null) {
            logger.warn(FIND_BY_TITLE_FAIL_LOG, title);
            throw new BookNotFoundException("Book not found with title: " + title);
        }

        logger.info(FIND_BY_TITLE_SUCCESS_LOG, title);
        return book;
    }

    public List<Book> findByAuthor(String author) {
        List<Book> books = bookDao.findBookByAuthor(author);

        if (books.isEmpty()) {
            logger.warn(NO_BOOKS, author);
        } else {
            List<Book> booksByAuthor = books.stream()
                    .filter(book -> isBookByAuthor(book, author))
                    .collect(Collectors.toList());

            if (!booksByAuthor.isEmpty()) {
                logger.info(FIND_BY_AUTHOR_SUCCESS_LOG, author);
            } else {
                logger.warn(FIND_BY_AUTHOR_FAIL_LOG, author);
                throw new AuthorNotFoundException(String.format("Books by author %s not found", author));
            }

            return booksByAuthor;
        }

        return books;
    }


    private boolean isBookByAuthor(Book book, String author) {
        String fullName = book.getAuthor().getName() + " " + book.getAuthor().getSurname();
        return author.equals(fullName);
    }

}