package org.example.validators.implementations.book;

import org.example.entities.Book;
import org.example.validators.BookValidator;
import org.example.validators.result.ValidationResult;

import static org.example.validators.messages.BookErrorMessages.TITLE_REQUIRED;
import static org.example.validators.messages.BookErrorMessages.TITLE_TOO_LONG;

public class TitleValidator implements BookValidator {

    @Override
    public ValidationResult validate(Book book) {
        ValidationResult result = new ValidationResult();

        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            result.addError(TITLE_REQUIRED);
        }
        if (book.getTitle().length() > 200) {
            result.addError(TITLE_TOO_LONG);
        }

        return result;
    }

}