package org.example.validators.implementations.book;

import org.example.entities.Book;
import org.example.validators.BookValidator;
import org.example.validators.result.ValidationResult;

import static org.example.validators.messages.BookErrorMessages.GENRE_TOO_LONG;

public class GenresValidator implements BookValidator {

    @Override
    public ValidationResult validate(Book book) {
        ValidationResult result = new ValidationResult();

        if (book.getGenres() != null) {
            for (String genre : book.getGenres()) {
                if (genre.length() > 50) {
                    result.addError(GENRE_TOO_LONG);
                }
            }
        }

        return result;
    }

}