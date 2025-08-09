package validators.book;

import org.example.exceptions.FieldValidationException;
import org.example.validators.implementations.book.PagesValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PagesValidatorTests {

    private PagesValidator validator;

    @BeforeEach
    void setUp() {
        validator = new PagesValidator();
    }

    @Test
    void validPageCount() {
        int pages = 100;
        assertDoesNotThrow(() -> validator.validate(pages));
    }

    @Test
    void nullPages() {
        assertDoesNotThrow(() -> validator.validate(null));
    }

    @Test
    void zeroPagesAllowed() {
        int pages = 0;
        assertDoesNotThrow(() -> validator.validate(pages));
    }

    @Test
    void negativePageCount() {
        int pages = -1;
        assertThrows(FieldValidationException.class, () -> validator.validate(pages));
    }

    @Test
    void pageCountTooHigh() {
        int pages = 2001;
        assertThrows(FieldValidationException.class, () -> validator.validate(pages));
    }

}