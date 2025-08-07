package org.example.validators.implementations.review;

import org.example.entities.Book;
import org.example.validators.BookValidator;
import org.example.validators.result.ValidationResult;

import static org.example.validators.messages.ReviewErrorMessages.INCORRECT_YEAR_READ_IN;

public class ReadInValidator implements BookValidator {

    @Override
    public ValidationResult validate(Book book) {
        ValidationResult result = new ValidationResult();
        int year = book.getReview().getReadIn();

        if (year < 1989 || year > 2025) {
            result.addError(INCORRECT_YEAR_READ_IN);
        }

        return result;
    }

}