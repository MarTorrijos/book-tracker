package org.example.validator.book;

import org.example.entities.Book;
import org.example.validator.BookValidator;
import org.example.validator.ValidationResult;

public class GenresValidator implements BookValidator {

    @Override
    public ValidationResult validate(Book book) {
        ValidationResult result = new ValidationResult();

        if (book.getGenres() != null) {
            for (String genre : book.getGenres()) {
                if (genre.length() > 50) {
                    result.addError("Genre '" + genre + "' is too long");
                }
            }
        }

        return result;
    }

}