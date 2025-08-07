package org.example.validators.implementations.book;

import org.example.entities.Book;
import org.example.validators.BookValidator;
import org.example.validators.result.ValidationResult;

import static org.example.validators.messages.BookErrorMessages.PAGES_NOT_POSITIVE;

public class PagesValidator implements BookValidator {

    @Override
    public ValidationResult validate(Book book) {
        ValidationResult result = new ValidationResult();

        if (book.getPages() != null && book.getPages() <= 0) {
            result.addError(PAGES_NOT_POSITIVE);
        }

        return result;
    }

}