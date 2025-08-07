package org.example.validators.implementations.book;

import org.example.exceptions.FieldValidationException;
import org.example.validators.FieldValidator;

import static org.example.validators.messages.BookErrorMessages.TITLE_REQUIRED;
import static org.example.validators.messages.BookErrorMessages.TITLE_TOO_LONG;

public class TitleValidator implements FieldValidator<String> {

    @Override
    public void validate(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new FieldValidationException(TITLE_REQUIRED);
        }
        if (title.length() > 200) {
            throw new FieldValidationException(TITLE_TOO_LONG);
        }
    }

}