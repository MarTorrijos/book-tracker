package booktracker.validators.review;

import booktracker.exceptions.FieldValidationException;
import booktracker.validators.implementations.review.NotesValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NotesValidatorTests {

    private NotesValidator validator;

    @BeforeEach
    void setUp() {
        validator = new NotesValidator();
    }

    @Test
    void validNotes() {
        String notes = "Very good book!";
        assertDoesNotThrow(() -> validator.validate(notes));
    }

    @Test
    void nullNotes() {
        assertDoesNotThrow(() -> validator.validate(null));
    }

    @Test
    void emptyNotes() {
        String notes = "";
        assertDoesNotThrow(() -> validator.validate(notes));
    }

    @Test
    void notesTooLong() {
        String notes = "A".repeat(1001);
        assertThrows(FieldValidationException.class, () -> validator.validate(notes));
    }

}