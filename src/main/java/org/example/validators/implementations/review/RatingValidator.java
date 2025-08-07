package org.example.validators.implementations.review;

import org.example.entities.Book;
import org.example.validators.BookValidator;
import org.example.validators.result.ValidationResult;

import static org.example.validators.messages.ReviewErrorMessages.INCORRECT_RATING;

public class RatingValidator implements BookValidator {

    @Override
    public ValidationResult validate(Book book) {
        ValidationResult result = new ValidationResult();
        float rating = book.getReview().getRating();

        if (rating < 1 || rating > 5) {
            result.addError(INCORRECT_RATING);
        }

        return result;
    }

}