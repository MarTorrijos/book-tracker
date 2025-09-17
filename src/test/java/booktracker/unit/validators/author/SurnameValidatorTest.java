package booktracker.unit.validators.author;

import booktracker.entities.Author;
import booktracker.exceptions.FieldValidationException;
import booktracker.testdata.BookDataProvider;
import booktracker.validators.implementations.author.AuthorNameValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SurnameValidatorTest {

    private AuthorNameValidator validator;
    private Author validAuthor;
    private Author authorSurnameTooLong;

    @BeforeEach
    void setUp() {
        validator = new AuthorNameValidator();
        validAuthor = BookDataProvider.validAuthor();
        authorSurnameTooLong = BookDataProvider.authorSurnameTooLong();
    }

    @Test
    void validAuthorSurname() {
        assertDoesNotThrow(() -> validator.validate(validAuthor.getSurname()));
    }

    @Test
    void nullAuthor() {
        assertThrows(FieldValidationException.class, () -> validator.validate(null));
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