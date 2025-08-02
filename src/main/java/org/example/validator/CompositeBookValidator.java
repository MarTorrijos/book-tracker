package org.example.validator;

import org.example.entities.Book;
import java.util.List;

public class CompositeBookValidator implements BookValidator {

    private final List<BookValidator> validators;

    public CompositeBookValidator(List<BookValidator> validators) {
        this.validators = validators;
    }

    @Override
    public ValidationResult validate(Book book) {
        ValidationResult combinedResult = new ValidationResult();

        for (BookValidator validator : validators) {
            ValidationResult result = validator.validate(book);
            result.getErrors().forEach(combinedResult::addError);
        }

        return combinedResult;
    }

}