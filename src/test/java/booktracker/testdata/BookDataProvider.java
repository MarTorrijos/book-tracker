package booktracker.testdata;

import booktracker.entities.Author;
import booktracker.entities.Book;
import org.bson.types.ObjectId;

import java.time.Year;

import static booktracker.testdata.AuthorDataProvider.*;

public final class BookDataProvider {

    public static final int CURRENT_YEAR = Year.now().getValue();
    public static final String DEFAULT_BOOK_TITLE = "Dune";
    public static final String LONG_TITLE = "A".repeat(201);
    public static final int MAX_PAGES = 2000;

    public static final String[] VALID_GENRES = {"Sci-fi", "Fantasy"};
    public static final String[] GENRE_TOO_LONG = {LONG_TITLE};
    public static final String[] ONE_GENRE_TOO_LONG = {"Sci-fi", "Fantasy", LONG_TITLE};

    public static final int VALID_PAGES = 100;
    public static final int ZERO_PAGES = 0;
    public static final int PAGE_COUNT_TOO_HIGH = MAX_PAGES + 1;

    public static final int VALID_PUBLISHED_IN = 2025;
    public static final int FUTURE_PUBLISHED_IN = CURRENT_YEAR + 1;

    public static final String TITLE_TOO_LONG = LONG_TITLE;
    public static final int NEGATIVE_NUMBER = -1;
    public static final String EMPTY_STRING = "";

    public static final Book VALID_BOOK = createBook(DEFAULT_BOOK_TITLE, validAuthor());
    public static final Book BOOK_WITH_NO_AUTHOR = createBook(DEFAULT_BOOK_TITLE, null);

    public static Book updatedBook(ObjectId id) {
        Book book = createBook("Dune Messiah", validAuthor());
        book.setId(id);
        return book;
    }

    public static Book createBook(String title, Author author) {
        Book book = new Book();
        book.setId(new ObjectId());
        book.setTitle(title);
        book.setAuthor(author);

        return book;
    }

}