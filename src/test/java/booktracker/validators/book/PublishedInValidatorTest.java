package booktracker.validators.book;

import booktracker.exceptions.FieldValidationException;
import booktracker.testdata.BookDataProvider;
import booktracker.validators.implementations.book.PublishedInValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PublishedInValidatorTest {

    private PublishedInValidator validator;

    @BeforeEach
    void setUp() {
        validator = new PublishedInValidator();
    }

    @Test
    void validPublishedIn() {
        assertDoesNotThrow(() -> validator.validate(BookDataProvider.validPublishedIn()));
    }

    @Test
    void nullPublishedIn() {
        assertDoesNotThrow(() -> validator.validate(null));
    }

    @Test
    void negativePublishedIn() {
        assertThrows(FieldValidationException.class, () -> validator.validate(BookDataProvider.negativeNumber()));
    }

    @Test
    void futurePublishedIn() {
        assertThrows(FieldValidationException.class, () -> validator.validate(BookDataProvider.futurePublishedIn()));
    }

}