package org.example.validators.implementations.book;

import org.example.exceptions.FieldValidationException;
import org.example.validators.FieldValidator;

import static org.example.validators.messages.BookErrorMessages.PAGES_NOT_POSITIVE;
import static org.example.validators.messages.BookErrorMessages.PAGE_COUNT_TOO_HIGH;

public class PagesValidator implements FieldValidator<Integer> {

    @Override
    public void validate(Integer pages) {
        if (pages != null) {
            if (pages < 0) {
                throw new FieldValidationException(PAGES_NOT_POSITIVE);
            } else if (pages > 2000) {
                throw new FieldValidationException(PAGE_COUNT_TOO_HIGH);
            }
        }
    }

}