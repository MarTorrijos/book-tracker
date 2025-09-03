package org.example.service.book;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.example.dao.author.AuthorDao;
import org.example.dao.book.BookDao;
import org.example.entities.Author;
import org.example.entities.Book;
import org.example.exceptions.BookNotFoundException;

import static org.example.log.BookLogMessages.*;

public class BookCrudService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private static final Logger logger = LogManager.getLogger(BookCrudService.class.getName());

    public BookCrudService(BookDao bookDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    public void save(Book book) {
        if (bookDao.bookExistsByTitle(book.getTitle())) {
            logger.warn(SAVE_FAIL_DUPLICATED_TITLE_LOG, book.getTitle());
            throw new IllegalArgumentException("Book with that title already exists.");
        }

        Author author = authorDao.findByName(book.getAuthor().getName());
        if (author == null) {
            author = authorDao.insert(book.getAuthor());
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