package org.example.validator.book;

import org.example.entities.Book;
import org.example.exceptions.InvalidBookException;
import org.example.validator.BookValidator;

public class PagesValidator implements BookValidator {

    @Override
    public void validate(Book book) {
        if (book.getPages() != null && book.getPages() <= 0) {
            throw new InvalidBookException("Pages must be a positive number");
        }
    }

}