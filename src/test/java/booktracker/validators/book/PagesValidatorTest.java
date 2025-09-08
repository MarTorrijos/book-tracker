package booktracker.validators.book;

import booktracker.exceptions.FieldValidationException;
import booktracker.testdata.DataProvider;
import booktracker.validators.implementations.book.PagesValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PagesValidatorTest {

    private PagesValidator validator;

    @BeforeEach
    void setUp() {
        validator = new PagesValidator();
    }

    @Test
    void validPageCount() {
        assertDoesNotThrow(() -> validator.validate(DataProvider.validPages()));
    }

    @Test
    void nullPages() {
        assertDoesNotThrow(() -> validator.validate(null));
    }

    @Test
    void zeroPagesAllowed() {
        assertDoesNotThrow(() -> validator.validate(DataProvider.zeroPages()));
    }

    @Test
    void negativePageCount() {
        assertThrows(FieldValidationException.class, () -> validator.validate(DataProvider.negativeNumber()));
    }

    @Test
    void pageCountTooHigh() {
        assertThrows(FieldValidationException.class, () -> validator.validate(DataProvider.pageCountTooHigh()));
    }

}