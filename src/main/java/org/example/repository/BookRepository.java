package org.example.repository;

import org.bson.types.ObjectId;
import org.example.entities.Book;
import org.example.validators.result.ValidationResult;

import java.util.List;

public interface BookRepository {

    void edit(ObjectId id, Book updatedBook);
    void delete(ObjectId id);

    ValidationResult save(Book book);
    List<Book> findAll();
    Book findById(ObjectId id);
    Book findByTitle(String title);
    List<Book> findByAuthor(String author);

}