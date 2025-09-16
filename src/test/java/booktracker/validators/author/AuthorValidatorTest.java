package booktracker.validators.author;

import booktracker.entities.Author;
import booktracker.exceptions.FieldValidationException;
import booktracker.testdata.BookDataProvider;
import booktracker.validators.implementations.author.AuthorNameValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorValidatorTest {

    private AuthorNameValidator validator;
    private Author validAuthor;
    private Author authorNameTooLong;
    private Author authorSurnameTooLong;

    @BeforeEach
    void setUp() {
        validator = new AuthorNameValidator();
        validAuthor = BookDataProvider.validAuthor();
        authorNameTooLong = BookDataProvider.authorNameTooLong();
        authorSurnameTooLong = BookDataProvider.authorSurnameTooLong();
    }

    @Test
    void validAuthor() {
        assertDoesNotThrow(() -> validator.validate(validAuthor.getName()));
    }

    @Test
    void nullAuthorName() {
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

    @Test
    void emptyAuthorSurname() {
        assertThrows(FieldValidationException.class, () -> validator.validate(BookDataProvider.emptyString()));
    }

    @Test
    @DisplayName("Should throw when author surname exceeds 200 characters")
    void authorSurnameTooLong() {
        assertThrows(FieldValidationException.class, () -> validator.validate(authorSurnameTooLong.getSurname()));
    }

}