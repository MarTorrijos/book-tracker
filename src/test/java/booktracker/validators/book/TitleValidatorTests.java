package booktracker.validators.book;

import booktracker.exceptions.FieldValidationException;
import booktracker.validators.implementations.book.TitleValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TitleValidatorTests {

    private TitleValidator validator;

    @BeforeEach
    void setUp() {
        validator = new TitleValidator();
    }

    @Test
    void validTitle() {
        String title = "Dune";
        assertDoesNotThrow(() -> validator.validate(title));
    }

    @Test
    void nullTitle() {
        assertThrows(FieldValidationException.class, () -> validator.validate(null));
    }

    @Test
    void emptyTitle() {
        String title = "";
        assertThrows(FieldValidationException.class, () -> validator.validate(title));
    }

    @Test
    void titleTooLong() {
        String title = "A".repeat(201);
        assertThrows(FieldValidationException.class, () -> validator.validate(title));
    }

}