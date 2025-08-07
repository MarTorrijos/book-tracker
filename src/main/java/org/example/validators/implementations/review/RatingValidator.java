package org.example.validators.implementations.review;

import org.example.exceptions.FieldValidationException;
import org.example.validators.FieldValidator;

import static org.example.validators.messages.ReviewErrorMessages.INCORRECT_RATING;

public class RatingValidator implements FieldValidator<Float> {

    @Override
    public void validate(Float rating) {
        if (rating < 1 || rating > 5) {
            throw new FieldValidationException(INCORRECT_RATING);
        }
    }

}