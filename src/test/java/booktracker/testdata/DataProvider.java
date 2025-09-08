package booktracker.testdata;

import booktracker.entities.Author;
import booktracker.entities.Book;
import org.bson.types.ObjectId;

import java.time.Year;
import java.util.List;

public class DataProvider {

    private static final int CURRENT_YEAR = Year.now().getValue();
    private static final String DEFAULT_AUTHOR_NAME = "Frank Herbert";
    private static final String LONG_AUTHOR_NAME = "A".repeat(301);
    private static final String DEFAULT_BOOK_TITLE = "Dune";
    private static final String LONG_TITLE = "A".repeat(201);
    private static final int MAX_PAGES = 2000;

    // Author

    public static Author validAuthor() {
        Author author = new Author();
        author.setName(DEFAULT_AUTHOR_NAME);
        return author;
    }

    public static Author authorNameTooLong() {
        Author author = new Author();
        author.setName(LONG_AUTHOR_NAME);
        return author;
    }

    // Book

    public static Book validBook() {
        Book book = new Book();
        book.setId(new ObjectId());
        book.setTitle(DEFAULT_BOOK_TITLE);
        book.setAuthor(validAuthor());
        return book;
    }

    public static Book bookWithNoAuthor() {
        Book book = new Book();
        book.setId(new ObjectId());
        book.setTitle(DEFAULT_BOOK_TITLE);
        return book;
    }

    public static Book updatedBook(ObjectId id) {
        Book book = new Book();
        book.setId(id);
        book.setTitle("Dune Messiah");
        book.setAuthor(validAuthor());
        return book;
    }

    public static Book duplicatedBook() {
        Book book = new Book();
        book.setTitle(DEFAULT_BOOK_TITLE);
        book.setAuthor(validAuthor());
        return book;
    }

    public static List<Book> bookListMock() {
        return List.of(
                Book.builder()
                        .id(new ObjectId())
                        .title("A Wizard of Earthsea")
                        .author(Author.builder().name("Ursula K. Le Guin").build())
                        .build(),
                Book.builder()
                        .id(new ObjectId())
                        .title("The Left Hand of Darkness")
                        .author(Author.builder().name("Ursula K. Le Guin").build())
                        .build(),
                Book.builder()
                        .id(new ObjectId())
                        .title("The Dispossessed")
                        .author(Author.builder().name("Ursula K. Le Guin").build())
                        .build()
        );
    }

    // Genres

    public static String[] validGenres() {
        return new String[]{"Sci-fi", "Fantasy"};
    }

    public static String[] genreTooLong() {
        return new String[]{LONG_TITLE};
    }

    public static String[] oneGenreTooLong() {
        return new String[]{"Sci-fi", "Fantasy", LONG_TITLE};
    }

    // Pages

    public static int validPages() {
        return 100;
    }

    public static int zeroPages() {
        return 0;
    }

    public static int pageCountTooHigh() {
        return MAX_PAGES + 1;
    }

    // Published in

    public static int validPublishedIn() {
        return 2025;
    }

    public static int futurePublishedIn() {
        return CURRENT_YEAR + 1;
    }

    // Title

    public static String titleTooLong() {
        return LONG_TITLE;
    }

    // Other

    public static int negativeNumber() {
        return -1;
    }

    public static String emptyString() {
        return "";
    }

}