package validators.review;

import org.example.exceptions.FieldValidationException;
import org.example.validators.implementations.review.RatingValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RatingValidatorTests {

    private RatingValidator validator;

    @BeforeEach
    void setUp() {
        validator = new RatingValidator();
    }

    @Test
    void validRating() {
        Float rating = 4.50f;
        assertDoesNotThrow(() -> validator.validate(rating));
    }

    @Test
    void nullRating() {
        assertDoesNotThrow(() -> validator.validate(null));
    }

    @Test
    void invalidRatingLessThan1() {
        Float rating = 0.99f;
        assertThrows(FieldValidationException.class, () -> validator.validate(rating));
    }

    @Test
    void invalidRatingMoreThan5() {
        Float rating = 5.01f;
        assertThrows(FieldValidationException.class, () -> validator.validate(rating));
    }

    @Test
    void tooManyDecimals() {
        Float rating = 4.550f;
        assertThrows(FieldValidationException.class, () -> validator.validate(rating));
    }

}
