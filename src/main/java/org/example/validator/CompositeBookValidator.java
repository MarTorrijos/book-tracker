package org.example.validator;

import org.example.entities.Book;

import java.util.List;

public class CompositeBookValidator implements BookValidator {

    private final List<BookValidator> validators;

    public CompositeBookValidator(List<BookValidator> validators) {
        this.validators = validators;
    }

    @Override
    public void validate(Book book) {
        for (BookValidator validator : validators) {
            validator.validate(book);
        }
    }

}