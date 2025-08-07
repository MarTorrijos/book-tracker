package org.example.validators.implementations.book;

import org.example.exceptions.FieldValidationException;
import org.example.validators.FieldValidator;

import static org.example.validators.messages.BookErrorMessages.GENRE_TOO_LONG;

public class GenresValidator implements FieldValidator<String[]> {

    @Override
    public void validate(String[] genres) {
        if (genres != null) {
            for (String genre : genres) {
                if (genre.length() > 50) {
                    throw new FieldValidationException(GENRE_TOO_LONG);
                }
            }
        }
    }

}