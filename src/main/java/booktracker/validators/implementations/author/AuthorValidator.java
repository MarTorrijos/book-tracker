package booktracker.validators.implementations.author;

import booktracker.exceptions.FieldValidationException;
import booktracker.validators.FieldValidator;

import static booktracker.validators.messages.AuthorErrorMessages.*;

public class AuthorValidator implements FieldValidator<String> {

    @Override
    public void validate(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new FieldValidationException(AUTHOR_NAME_REQUIRED);
        } else if (name.length() > 300) {
            throw new FieldValidationException(AUTHOR_NAME_TOO_LONG);
        }
    }

}