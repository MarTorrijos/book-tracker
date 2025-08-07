package org.example.validators.implementations.book;

import org.example.exceptions.FieldValidationException;
import org.example.validators.FieldValidator;

import static org.example.validators.messages.BookErrorMessages.PAGES_NOT_POSITIVE;

public class PagesValidator implements FieldValidator<Integer> {

    @Override
    public void validate(Integer pages) {
        if (pages != null && pages <= 0) {
            throw new FieldValidationException(PAGES_NOT_POSITIVE);
        }
    }

}