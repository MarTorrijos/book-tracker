package org.example.validators.implementations.review;

import org.example.exceptions.FieldValidationException;
import org.example.validators.FieldValidator;

import static org.example.validators.messages.ReviewErrorMessages.NOTES_TOO_LONG;

public class NotesValidator implements FieldValidator<String> {

    @Override
    public void validate(String notes) {
        if (notes.length() > 1000) {
            throw new FieldValidationException(NOTES_TOO_LONG);
        }
    }

}
