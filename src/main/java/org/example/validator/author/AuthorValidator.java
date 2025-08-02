package org.example.validator.author;

import org.example.entities.Book;
import org.example.validator.BookValidator;
import org.example.validator.ValidationResult;

public class AuthorValidator implements BookValidator {

    @Override
    public ValidationResult validate(Book book) {
        ValidationResult result = new ValidationResult();

        if (book.getAuthor() == null) {
            result.addError("An author is required");
        } else if (book.getAuthor().getName().length() > 300) {
            result.addError("Author name too long. Needs to be less than 300 characters");
        }

        return result;
    }

}