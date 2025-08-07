package org.example.validators.implementations.review;

import org.example.entities.Book;
import org.example.validators.BookValidator;
import org.example.validators.result.ValidationResult;

import static org.example.validators.messages.ReviewErrorMessages.NOTES_TOO_LONG;

public class NotesValidator implements BookValidator {

    @Override
    public ValidationResult validate(Book book) {
        ValidationResult result = new ValidationResult();

        if (book.getReview().getNotes().length() > 1000) {
            result.addError(NOTES_TOO_LONG);
        }

        return result;
    }

}
