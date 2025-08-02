package org.example.validator.book;

import org.example.entities.Book;
import org.example.validator.BookValidator;
import org.example.validator.ValidationResult;

import java.time.Year;

public class PublishedInValidator implements BookValidator {

    private final int CURRENT_YEAR = Year.now().getValue();

    @Override
    public ValidationResult validate(Book book) {
        ValidationResult result = new ValidationResult();

        if (book.getPublishedIn() != null && (book.getPublishedIn() < 0 || book.getPublishedIn() > CURRENT_YEAR)) {
            result.addError("Published year must be a valid year (between 0 and " + CURRENT_YEAR + ")");
        }

        return result;
    }

}