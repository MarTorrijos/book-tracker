package booktracker.validators.implementations.book;

import booktracker.exceptions.FieldValidationException;
import booktracker.validators.FieldValidator;

import static booktracker.validators.messages.BookErrorMessages.TITLE_REQUIRED;
import static booktracker.validators.messages.BookErrorMessages.TITLE_TOO_LONG;

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