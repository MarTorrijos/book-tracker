package booktracker.validators.book;

import booktracker.exceptions.FieldValidationException;
import booktracker.testdata.BookDataProvider;
import booktracker.validators.implementations.book.TitleValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TitleValidatorTest {

    private TitleValidator validator;

    @BeforeEach
    void setUp() {
        validator = new TitleValidator();
    }

    @Test
    void validTitle() {
        assertDoesNotThrow(() -> validator.validate(BookDataProvider.validBook().getTitle()));
    }

    @Test
    void nullTitle() {
        assertThrows(FieldValidationException.class, () -> validator.validate(null));
    }

    @Test
    void emptyTitle() {
        assertThrows(FieldValidationException.class, () -> validator.validate(BookDataProvider.emptyString()));
    }

    @Test
    void titleTooLong() {
        assertThrows(FieldValidationException.class, () -> validator.validate(BookDataProvider.titleTooLong()));
    }

}