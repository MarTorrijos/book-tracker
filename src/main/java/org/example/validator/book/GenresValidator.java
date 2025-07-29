package org.example.validator.book;

import org.example.entities.Book;
import org.example.exceptions.InvalidBookException;
import org.example.validator.BookValidator;

public class GenresValidator implements BookValidator {

    @Override
    public void validate(Book book) {
        if (book.getGenres() != null) {
            for (String genre : book.getGenres()) {
                if (genre.length() > 50) {
                    throw new InvalidBookException("Genre '" + genre + "' is too long");
                }
            }
        }
    }

}
