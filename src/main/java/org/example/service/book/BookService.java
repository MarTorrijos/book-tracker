package org.example.service.book;

import org.bson.types.ObjectId;
import org.example.dao.author.AuthorDao;
import org.example.dao.book.BookDao;
import org.example.entities.Author;
import org.example.entities.Book;
import org.example.exceptions.BookNotFoundException;

import java.util.List;

public class BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;

    public BookService(BookDao bookDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    public void save(Book book) {
        if (bookDao.existsByTitle(book.getTitle())) {
            throw new IllegalArgumentException("Book with that title already exists.");
        }

        Author existingAuthor = authorDao.findByName(book.getAuthor().getName());

        if (existingAuthor == null) {
            existingAuthor = authorDao.insert(book.getAuthor());
        }

        book.setAuthor(existingAuthor);

        bookDao.insert(book);
    }

    public void edit(ObjectId id, Book updatedBook) {
        if (bookDao.findById(id) == null) {
            throw new BookNotFoundException("Book not found");
        }
        bookDao.update(id, updatedBook);
    }

    public void delete(ObjectId id) {
        if (bookDao.findById(id) == null) {
            throw new BookNotFoundException("Book not found");
        }
        bookDao.delete(id);
    }

    public List<Book> findAll() {
        return bookDao.findAll();
    }

    public Book findById(ObjectId id) {
        Book book = bookDao.findById(id);
        if (book == null) {
            throw new BookNotFoundException("Book not found");
        }
        return book;
    }

    public Book findByTitle(String title) {
        Book book = bookDao.findByTitle(title);
        if (book == null) {
            throw new BookNotFoundException("Book not found");
        }
        return book;
    }

    public List<Book> findByAuthor(String author) {
        List<Book> books = bookDao.findByAuthor(author);
        if (books.isEmpty()) {
            throw new BookNotFoundException("No books found for that author");
        }
        return books;
    }

}