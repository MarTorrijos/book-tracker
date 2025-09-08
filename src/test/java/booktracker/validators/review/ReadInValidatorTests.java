package booktracker.validators.review;

import booktracker.exceptions.FieldValidationException;
import booktracker.validators.implementations.review.ReadInValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReadInValidatorTests {

    private ReadInValidator validator;
    private final int CURRENT_YEAR = Year.now().getValue();

    @BeforeEach
    void setUp() {
        validator = new ReadInValidator();
    }

    @Test
    void validReadIn() {
        int readIn = CURRENT_YEAR;
        assertDoesNotThrow(() -> validator.validate(readIn));
    }

    @Test
    void nullReadIn() {
        assertDoesNotThrow(() -> validator.validate(null));
    }

    @Test
    void readBeforeUserWasBorn() {
        int readIn = 1990;
        assertThrows(FieldValidationException.class, () -> validator.validate(readIn));
    }

    @Test
    void readInAFutureYear() {
        int readIn = CURRENT_YEAR + 1;
        assertThrows(FieldValidationException.class, () -> validator.validate(readIn));
    }

}