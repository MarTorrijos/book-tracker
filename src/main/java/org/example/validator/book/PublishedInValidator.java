package org.example.validator.book;

import org.example.entities.Book;
import org.example.exceptions.InvalidBookException;
import org.example.validator.BookValidator;

import java.time.Year;

public class PublishedInValidator implements BookValidator {

    private final int CURRENT_YEAR = Year.now().getValue();

    @Override
    public void validate(Book book) {
        if (book.getPublishedIn() != null && (book.getPublishedIn() < 0 || book.getPublishedIn() > CURRENT_YEAR)) {
            throw new InvalidBookException("Published year must be a valid year (between 0 and " + CURRENT_YEAR + ")");
        }
    }

}