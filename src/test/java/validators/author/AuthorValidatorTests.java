package validators.author;

import org.example.exceptions.FieldValidationException;
import org.example.validators.implementations.author.AuthorValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorValidatorTests {

    private AuthorValidator validator;

    @BeforeEach
    void setUp() {
        validator = new AuthorValidator();
    }

    @Test
    void correctAuthor() {
        String name = "Frank Herbert";
        assertDoesNotThrow(() -> validator.validate(name));
    }

    @Test
    void nullAuthorName() {
        assertThrows(FieldValidationException.class, () -> validator.validate(null));
    }

    @Test
    void noAuthorName() {
        String name = "";
        assertThrows(FieldValidationException.class, () -> validator.validate(name));
    }

    @Test
    void authorNameTooLong() {
        String name = "A".repeat(301);
        assertThrows(FieldValidationException.class, () -> validator.validate(name));
    }

}