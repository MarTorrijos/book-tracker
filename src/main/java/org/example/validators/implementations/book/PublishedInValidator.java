package org.example.validators.implementations.book;

import org.example.exceptions.FieldValidationException;
import org.example.validators.FieldValidator;

import java.time.Year;

import static org.example.validators.messages.BookErrorMessages.PUBLISHED_IN_NOT_VALID;

public class PublishedInValidator implements FieldValidator<Integer> {

    private final int CURRENT_YEAR = Year.now().getValue();

    @Override
    public void validate(Integer year) {
        if (year != null && (year < 0 || year > CURRENT_YEAR)) {
            throw new FieldValidationException (String.format(PUBLISHED_IN_NOT_VALID, CURRENT_YEAR));
        }
    }

}