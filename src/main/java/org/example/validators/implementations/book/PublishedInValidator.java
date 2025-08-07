package org.example.validators.implementations.book;

import org.example.entities.Book;
import org.example.validators.BookValidator;
import org.example.validators.result.ValidationResult;

import java.time.Year;

import static org.example.validators.messages.BookErrorMessages.PUBLISHED_IN_NOT_VALID;

public class PublishedInValidator implements BookValidator {

    private final int CURRENT_YEAR = Year.now().getValue();

    @Override
    public ValidationResult validate(Book book) {
        ValidationResult result = new ValidationResult();
        Integer year = book.getPublishedIn();

        if (year != null && (year < 0 || year > CURRENT_YEAR)) {
            String errorMessage = String.format(PUBLISHED_IN_NOT_VALID, CURRENT_YEAR);
            result.addError(errorMessage);
        }

        return result;
    }

}