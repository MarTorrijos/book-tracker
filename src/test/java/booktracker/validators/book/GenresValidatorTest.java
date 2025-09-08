package booktracker.validators.book;

import booktracker.exceptions.FieldValidationException;
import booktracker.testdata.DataProvider;
import booktracker.validators.implementations.book.GenresValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static booktracker.validators.messages.BookErrorMessages.GENRE_TOO_LONG;
import static org.junit.jupiter.api.Assertions.*;

public class GenresValidatorTest {

    private GenresValidator validator;

    @BeforeEach
    void setUp() {
        validator = new GenresValidator();
    }

    @Test
    void validGenres() {
        assertDoesNotThrow(() -> validator.validate(DataProvider.validGenres()));
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
        assertThrows(FieldValidationException.class,
                () -> validator.validate(DataProvider.genreTooLong()));
    }

    @Test
    @DisplayName("Should throw when one of the genres exceeds 50 characters")
    void multipleGenresOneTooLong() {
        FieldValidationException exception = assertThrows(
                FieldValidationException.class,
                () -> validator.validate(DataProvider.oneGenreTooLong()));

        assertTrue(exception.getMessage().startsWith(GENRE_TOO_LONG));
    }

}