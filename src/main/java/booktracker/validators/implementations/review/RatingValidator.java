package booktracker.validators.implementations.review;

import booktracker.exceptions.FieldValidationException;
import booktracker.validators.FieldValidator;

import static booktracker.validators.messages.ReviewErrorMessages.INCORRECT_RATING;

public class RatingValidator implements FieldValidator<Float> {

    @Override
    public void validate(Float rating) {
        if (rating != null) {
            if (rating < 1
                    || rating > 5
                    || Math.round(rating * 100) != rating * 100) {
                throw new FieldValidationException(INCORRECT_RATING);
            }
        }
    }

}