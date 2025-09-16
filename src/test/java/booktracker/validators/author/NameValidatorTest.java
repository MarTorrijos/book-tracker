package booktracker.validators.author;

import booktracker.entities.Author;
import booktracker.exceptions.FieldValidationException;
import booktracker.testdata.BookDataProvider;
import booktracker.validators.implementations.author.AuthorNameValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NameValidatorTest {

    private AuthorNameValidator validator;
    private Author validAuthor;
    private Author authorNameTooLong;

    @BeforeEach
    void setUp() {
        validator = new AuthorNameValidator();
        validAuthor = BookDataProvider.validAuthor();
        authorNameTooLong = BookDataProvider.authorNameTooLong();
    }

    @Test
    void validAuthorName() {
        assertDoesNotThrow(() -> validator.validate(validAuthor.getName()));
    }

    @Test
    void nullAuthor() {
        assertThrows(FieldValidationException.class, () -> validator.validate(null));
    }

    @Test
    void emptyAuthorName() {
        assertThrows(FieldValidationException.class, () -> validator.validate(BookDataProvider.emptyString()));
    }

    @Test
    @DisplayName("Should throw when author name exceeds 100 characters")
    void authorNameTooLong() {
        assertThrows(FieldValidationException.class, () -> validator.validate(authorNameTooLong.getName()));
    }

}