package booktracker.validators.implementations.review;

import booktracker.exceptions.FieldValidationException;
import booktracker.validators.FieldValidator;

import static booktracker.validators.messages.ReviewErrorMessages.NOTES_TOO_LONG;

public class NotesValidator implements FieldValidator<String> {

    @Override
    public void validate(String notes) {
        if (notes != null && notes.length() > 1000) {
            throw new FieldValidationException(NOTES_TOO_LONG);
        }
    }

}
