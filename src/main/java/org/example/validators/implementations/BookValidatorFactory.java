package org.example.validators.implementations;

import org.example.validators.BookValidator;
import org.example.validators.implementations.author.AuthorValidator;
import org.example.validators.implementations.book.GenresValidator;
import org.example.validators.implementations.book.PagesValidator;
import org.example.validators.implementations.book.PublishedInValidator;
import org.example.validators.implementations.book.TitleValidator;

import java.util.List;

public class BookValidatorFactory {

    public static CompositeBookValidator create() {
        List<BookValidator> validators = List.of(
                new AuthorValidator(),
                new GenresValidator(),
                new PagesValidator(),
                new PublishedInValidator(),
                new TitleValidator()
        );
        return new CompositeBookValidator(validators);
    }

}