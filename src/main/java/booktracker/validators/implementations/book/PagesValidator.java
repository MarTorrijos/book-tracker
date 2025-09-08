package booktracker.validators.implementations.book;

import booktracker.exceptions.FieldValidationException;
import booktracker.validators.FieldValidator;

import static booktracker.validators.messages.BookErrorMessages.PAGES_NOT_POSITIVE;
import static booktracker.validators.messages.BookErrorMessages.PAGE_COUNT_TOO_HIGH;

public class PagesValidator implements FieldValidator<Integer> {

    @Override
    public void validate(Integer pages) {
        if (pages != null) {
            if (pages < 0) {
                throw new FieldValidationException(PAGES_NOT_POSITIVE);
            } else if (pages > 2000) {
                throw new FieldValidationException(PAGE_COUNT_TOO_HIGH);
            }
        }
    }

}