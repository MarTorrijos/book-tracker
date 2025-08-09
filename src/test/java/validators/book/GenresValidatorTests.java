package validators.book;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.exceptions.FieldValidationException;
import org.example.validators.implementations.book.GenresValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.validators.messages.BookErrorMessages.GENRE_TOO_LONG;
import static org.junit.jupiter.api.Assertions.*;

public class GenresValidatorTests {

    private GenresValidator validator;
    private static final Logger logger = LogManager.getLogger();

    @BeforeEach
    void setUp() {
        validator = new GenresValidator();
    }

    @Test
    void correctGenres() {
        String[] genres = {"Sci-fi", "Fantasy"};
        assertDoesNotThrow(() -> validator.validate(genres));
    }

    @Test
    void nullGenres() {
        assertDoesNotThrow(() -> validator.validate(null));
    }

    @Test
    void emptyGenres() {
        String[] genres = {};
        assertDoesNotThrow(() -> validator.validate(genres));
    }

    @Test
    @DisplayName("Should throw when genre exceeds 50 characters")
    void genreTooLong() {
        String tooLongGenre = "A".repeat(51);
        assertThrows(FieldValidationException.class,
                () -> validator.validate(new String[]{tooLongGenre}));
    }

    @Test
    @DisplayName("Should throw when one of the genres exceeds 50 characters")
    void multipleGenresOneTooLong() {
        String[] genres = {"Sci-fi", "Fantasy", "A".repeat(51)};
        FieldValidationException exception = assertThrows(
                FieldValidationException.class,
                () -> validator.validate(genres));

        assertTrue(exception.getMessage().startsWith(GENRE_TOO_LONG));
    }

}