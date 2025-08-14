package org.example.service.book;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.example.dao.author.AuthorDao;
import org.example.dao.book.BookDao;
import org.example.entities.Author;
import org.example.entities.Book;
import org.example.exceptions.BookNotFoundException;

import java.util.List;

import static org.example.log.BookLogMessages.*;

public class BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private static final Logger logger = LogManager.getLogger(BookService.class.getName());

    public BookService(BookDao bookDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    public void save(Book book) {
        if (bookDao.bookExistsByTitle(book.getTitle())) {
            logger.warn(SAVE_FAIL_DUPLICATED_TITLE_LOG, book.getTitle());
            throw new IllegalArgumentException("Book with that title already exists.");
        }

        Author existingAuthor = authorDao.findByName(book.getAuthor().getName());
        if (existingAuthor == null) {
            existingAuthor = authorDao.insert(book.getAuthor());
            logger.info(SAVE_SUCCESS_LOG, book.getTitle());
        }
        book.setAuthor(existingAuthor);

        bookDao.insert(book);
        logger.info(SAVE_SUCCESS_LOG, book.getTitle());
    }

    public void update(ObjectId id, Book updatedBook) {
        if (bookDao.findBookById(id) == null) {
            logger.warn(UPDATE_FAIL_LOG, updatedBook.getTitle());
            throw new BookNotFoundException("Book not found");
        }
        bookDao.update(id, updatedBook);
        logger.info(UPDATE_SUCCESS_LOG, updatedBook.getTitle());
    }

    public void delete(ObjectId id) {
        if (bookDao.findBookById(id) == null) {
            logger.warn(DELETE_FAIL_LOG, id.toHexString());
            throw new BookNotFoundException("Book not found");
        }
        bookDao.delete(id);
        logger.info(DELETE_SUCCESS_LOG, id.toHexString());
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