package booktracker.validators.implementations.book;

import booktracker.exceptions.FieldValidationException;
import booktracker.validators.FieldValidator;

import static booktracker.validators.messages.BookErrorMessages.GENRE_TOO_LONG;

public class GenresValidator implements FieldValidator<String[]> {

    @Override
    public void validate(String[] genres) {
        if (genres != null) {
            for (String genre : genres) {
                if (genre.length() > 50) {
                    throw new FieldValidationException(GENRE_TOO_LONG + ": " + genre);
                }
            }
        }
    }

}