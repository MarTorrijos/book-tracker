package booktracker.testdata;

import booktracker.entities.Author;
import org.bson.types.ObjectId;

public class AuthorDataProvider {

    private static final String DEFAULT_AUTHOR_NAME = "Frank";
    private static final String DEFAULT_AUTHOR_SURNAME = "Herbert";
    private static final String LONG_AUTHOR_NAME = "A".repeat(101);
    private static final String LONG_AUTHOR_SURNAME = "A".repeat(201);

    public static Author validAuthor() {
        return Author.builder()
                .id(new ObjectId())
                .name(DEFAULT_AUTHOR_NAME)
                .surname(DEFAULT_AUTHOR_SURNAME)
                .build();
    }

    public static Author authorNameTooLong() {
        return Author.builder()
                .id(new ObjectId())
                .name(LONG_AUTHOR_NAME)
                .build();
    }

    public static Author authorSurnameTooLong() {
        return Author.builder()
                .id(new ObjectId())
                .surname(LONG_AUTHOR_SURNAME)
                .build();
    }

}