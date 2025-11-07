package booktracker.testdata;

import booktracker.entities.Author;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AuthorDataProvider {

    private static final String DEFAULT_AUTHOR_NAME = "Frank";
    private static final String DEFAULT_AUTHOR_SURNAME = "Herbert";
    public static final String OTHER_AUTHOR_SURNAME = "Gamma";
    private static final String LONG_AUTHOR_NAME = "A".repeat(101);
    private static final String LONG_AUTHOR_SURNAME = "A".repeat(201);

    private static Author createAuthor(String name, String surname) {
        return Author.builder()
                .id(new ObjectId())
                .name(name)
                .surname(surname)
                .build();
    }

    public static Author validAuthor() {
        return createAuthor(DEFAULT_AUTHOR_NAME, DEFAULT_AUTHOR_SURNAME);
    }

    public static Author authorNameTooLong() {
        return createAuthor(LONG_AUTHOR_NAME, DEFAULT_AUTHOR_SURNAME);
    }

    public static Author authorSurnameTooLong() {
        return createAuthor(DEFAULT_AUTHOR_NAME, LONG_AUTHOR_SURNAME);
    }

    public static Author newAuthorData(String newName, Author baseAuthor) {
        return createAuthor(newName, baseAuthor.getSurname());
    }

    public static Author updatedAuthorFrom(Author baseAuthor, Author newData) {
        return Author.builder()
                .id(baseAuthor.getId())
                .name(newData.getName())
                .surname(newData.getSurname())
                .build();
    }

    public static List<Author> authorList() {
        return Stream.of(
                createAuthor(DEFAULT_AUTHOR_NAME, DEFAULT_AUTHOR_SURNAME),
                createAuthor(DEFAULT_AUTHOR_NAME, DEFAULT_AUTHOR_SURNAME)
        ).collect(Collectors.toList());
    }

    public static List<Author> authorListWithDifferentNames() {
        return Stream.of(
                createAuthor(DEFAULT_AUTHOR_NAME, DEFAULT_AUTHOR_SURNAME),
                createAuthor("George", "Orwell")
        ).collect(Collectors.toList());
    }

}