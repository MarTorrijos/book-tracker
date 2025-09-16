package booktracker.validators.implementations.author;

import booktracker.exceptions.FieldValidationException;
import booktracker.validators.FieldValidator;

import static booktracker.validators.messages.AuthorErrorMessages.AUTHOR_SURNAME_REQUIRED;
import static booktracker.validators.messages.AuthorErrorMessages.AUTHOR_SURNAME_TOO_LONG;

public class AuthorSurnameValidator implements FieldValidator<String> {

    @Override
    public void validate(String surname) {
        if (surname == null || surname.trim().isEmpty()) {
            throw new FieldValidationException(AUTHOR_SURNAME_REQUIRED);
        } else if (surname.length() > 200) {
            throw new FieldValidationException(AUTHOR_SURNAME_TOO_LONG);
        }
    }

}