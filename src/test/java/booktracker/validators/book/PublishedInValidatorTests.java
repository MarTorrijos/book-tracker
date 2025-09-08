package booktracker.validators.book;

import booktracker.exceptions.FieldValidationException;
import booktracker.validators.implementations.book.PublishedInValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PublishedInValidatorTests {

    private PublishedInValidator validator;
    private final int CURRENT_YEAR = Year.now().getValue();

    @BeforeEach
    void setUp() {
        validator = new PublishedInValidator();
    }

    @Test
    void validPublishedIn() {
        int year = 2025;
        assertDoesNotThrow(() -> validator.validate(year));
    }

    @Test
    void nullPublishedIn() {
        assertDoesNotThrow(() -> validator.validate(null));
    }

    @Test
    void negativePublishedIn() {
        int year = -1;
        assertThrows(FieldValidationException.class, () -> validator.validate(year));
    }

    @Test
    void futurePublishedIn() {
        int year = CURRENT_YEAR + 1;
        assertThrows(FieldValidationException.class, () -> validator.validate(year));
    }

}