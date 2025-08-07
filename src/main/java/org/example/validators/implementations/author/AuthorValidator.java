package org.example.validators.implementations.author;

import org.example.entities.Book;
import org.example.validators.BookValidator;
import org.example.validators.result.ValidationResult;

import static org.example.validators.messages.AuthorErrorMessages.AUTHOR_REQUIRED;
import static org.example.validators.messages.AuthorErrorMessages.AUTHOR_TOO_LONG;

public class AuthorValidator implements BookValidator {

    @Override
    public ValidationResult validate(Book book) {
        ValidationResult result = new ValidationResult();

        if (book.getAuthor() == null) {
            result.addError(AUTHOR_REQUIRED);
        } else if (book.getAuthor().getName().length() > 300) {
            result.addError(AUTHOR_TOO_LONG);
        }

        return result;
    }

}