package org.example.service.book;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.example.dao.book.BookDao;
import org.example.entities.Book;
import org.example.exceptions.BookNotFoundException;

import java.util.List;

import static org.example.log.BookLogMessages.FIND_BY_AUTHOR_FAIL;
import static org.example.log.BookLogMessages.FIND_BY_AUTHOR_SUCCESS;
import static org.example.log.BookLogMessages.FIND_BY_TITLE_SUCCESS_LOG;
import static org.example.log.BookLogMessages.FIND_BY_TITLE_FAIL_LOG;
import static org.example.log.BookLogMessages.FIND_BY_ID_FAIL_LOG;
import static org.example.log.BookLogMessages.FIND_BY_ID_SUCCESS_LOG;

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
            throw new BookNotFoundException("Book not found");
        }
        logger.info(FIND_BY_ID_SUCCESS_LOG, id.toHexString());
        return book;
    }

    // TODO: findById !!!

    public Book findByTitle(String title) {
        Book book = bookDao.findBookByTitle(title);

        if (book == null) {
            logger.warn(FIND_BY_TITLE_FAIL_LOG, title);
            throw new BookNotFoundException("Book not found");
        }
        logger.info(FIND_BY_TITLE_SUCCESS_LOG, title);
        return book;
    }

    public List<Book> findByAuthor(String author) {
        List<Book> books = bookDao.findBookByAuthor(author);

        if (books.isEmpty()) {
            logger.warn(FIND_BY_AUTHOR_FAIL, author);
            throw new BookNotFoundException("No books found for that author");
        }
        logger.info(FIND_BY_AUTHOR_SUCCESS, author);
        return books;
    }

}