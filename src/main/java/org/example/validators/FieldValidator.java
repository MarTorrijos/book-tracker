package org.example.validators;

public interface FieldValidator<T> {

    void validate(T value);

}