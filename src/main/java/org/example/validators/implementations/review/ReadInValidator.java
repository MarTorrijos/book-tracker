package org.example.validators.implementations.review;

import org.example.exceptions.FieldValidationException;
import org.example.validators.FieldValidator;

import static org.example.validators.messages.ReviewErrorMessages.INCORRECT_YEAR_READ_IN;

public class ReadInValidator implements FieldValidator<Integer> {

    @Override
    public void validate(Integer year) {
        if (year < 1989 || year > 2025) {
            throw new FieldValidationException(INCORRECT_YEAR_READ_IN);
        }
    }

}