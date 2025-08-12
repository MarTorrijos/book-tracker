package org.example.dao.book;

import org.bson.types.ObjectId;
import org.example.entities.Book;

import java.util.List;

public interface BookDao {

    void insert(Book book);
    void update(ObjectId id, Book book);
    void delete(ObjectId id);

    List<Book> findAll();
    Book findById(ObjectId id);
    Book findByTitle(String title);
    List<Book> findByAuthor(String author);
    boolean existsByTitle(String title);

}