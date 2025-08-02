package org.example.validator.book;

import org.example.entities.Book;
import org.example.validator.BookValidator;
import org.example.validator.ValidationResult;

public class PagesValidator implements BookValidator {

    @Override
    public ValidationResult validate(Book book) {
        ValidationResult result = new ValidationResult();

        if (book.getPages() != null && book.getPages() <= 0) {
            result.addError("Pages must be a positive number");
        }

        return result;
    }

}