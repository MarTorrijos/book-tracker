package booktracker.unit.validators.book;

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
        assertDoesNotThrow(() -> validator.validate(BookDataProvider.VALID_PUBLISHED_IN));
    }

    @Test
    void nullPublishedIn() {
        assertDoesNotThrow(() -> validator.validate(null));
    }

    @Test
    void negativePublishedIn() {
        assertThrows(FieldValidationException.class, () -> validator.validate(BookDataProvider.NEGATIVE_NUMBER));
    }

    @Test
    void futurePublishedIn() {
        assertThrows(FieldValidationException.class, () -> validator.validate(BookDataProvider.FUTURE_PUBLISHED_IN));
    }

}