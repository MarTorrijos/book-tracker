package org.example.validator.author;

import org.example.entities.Book;
import org.example.exceptions.InvalidAuthorException;
import org.example.exceptions.InvalidBookException;
import org.example.validator.BookValidator;

public class AuthorValidator implements BookValidator {

    @Override
    public void validate(Book book) {
        if (book.getAuthor() == null) {
            throw new InvalidAuthorException("Book author is required");
        }
        if (book.getAuthor().getName().length() > 300) {
            throw new InvalidBookException("Book author can't exceed 300 characters");
        }
    }

}
