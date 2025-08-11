package org.example.validators.implementations.review;

import org.example.exceptions.FieldValidationException;
import org.example.validators.FieldValidator;

import java.time.Year;

import static org.example.validators.messages.ReviewErrorMessages.INCORRECT_YEAR_READ_IN;

public class ReadInValidator implements FieldValidator<Integer> {

    private final int CURRENT_YEAR = Year.now().getValue();

    @Override
    public void validate(Integer year) {
        int USER_BORN_IN = 1989;

        if (year != null) {
            if (year < USER_BORN_IN || year > CURRENT_YEAR) {
                throw new FieldValidationException(INCORRECT_YEAR_READ_IN);
            }
        }
    }

}