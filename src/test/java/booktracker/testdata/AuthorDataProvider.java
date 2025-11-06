package booktracker.testdata;

import booktracker.entities.Author;
import org.bson.types.ObjectId;

public class AuthorDataProvider {

    private static final String DEFAULT_AUTHOR_NAME = "Frank";
    private static final String DEFAULT_AUTHOR_SURNAME = "Herbert";
    private static final String LONG_AUTHOR_NAME = "A".repeat(101);
    private static final String LONG_AUTHOR_SURNAME = "A".repeat(201);

    public static Author validAuthor() {
        Author author = new Author();
        author.setId(new ObjectId());
        author.setName(DEFAULT_AUTHOR_NAME);
        author.setSurname(DEFAULT_AUTHOR_SURNAME);
        return author;
    }

    public static Author authorNameTooLong() {
        Author author = new Author();
        author.setId(new ObjectId());
        author.setName(LONG_AUTHOR_NAME);
        return author;
    }

    public static Author authorSurnameTooLong() {
        Author author = new Author();
        author.setId(new ObjectId());
        author.setSurname(LONG_AUTHOR_SURNAME);
        return author;
    }

}