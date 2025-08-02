package org.example.validator;

import org.example.entities.Book;

public interface BookValidator {

    ValidationResult validate(Book book);

}