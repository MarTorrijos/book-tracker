package org.example.validator.book;

import org.example.entities.Book;
import org.example.validator.BookValidator;
import org.example.validator.ValidationResult;

public class TitleValidator implements BookValidator {

    @Override
    public ValidationResult validate(Book book) {
        ValidationResult result = new ValidationResult();

        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            result.addError("Book title can't be empty");
        }
        if (book.getTitle().length() > 200) {
            result.addError("Book title can't exceed 200 characters");
        }

        return result;
    }

}