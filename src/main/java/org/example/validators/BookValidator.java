package org.example.validators;

import org.example.entities.Book;
import org.example.validators.result.ValidationResult;

public interface BookValidator {

    ValidationResult validate(Book book);

}