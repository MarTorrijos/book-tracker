package booktracker.testdata;

import booktracker.entities.Author;
import booktracker.entities.Book;
import org.bson.types.ObjectId;

import java.time.Year;

public class DataProvider {

    private static final int CURRENT_YEAR = Year.now().getValue();

    // Author

    public static Author validAuthor() {
        Author author = new Author();
        author.setName("Frank Herbert");
        return author;
    }

    public static Author authorNameTooLong() {
        Author author = new Author();
        author.setName("A".repeat(301));
        return author;
    }

    // Book

    public static Book validBook() {
        Book book = new Book();
        book.setId(new ObjectId());
        book.setTitle("Dune");
        book.setAuthor(validAuthor());
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
        book.setTitle("Dune");
        book.setAuthor(validAuthor());
        return book;
    }

    // Genres

    public static String[] validGenres() {
        return new String[]{"Sci-fi", "Fantasy"};
    }

    public static String[] genreTooLong() {
        return new String[]{"A".repeat(51)};
    }

    public static String[] oneGenreTooLong() {
        return new String[]{"Sci-fi", "Fantasy", "A".repeat(51)};
    }

    // Pages

    public static int validPages() {
        return 100;
    }

    public static int zeroPages() {
        return 0;
    }

    public static int pageCountTooHigh() {
        return 2001;
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
        return "A".repeat(201);
    }

    // Other

    public static int negativeNumber() {
        return -1;
    }

    public static String emptyString() {
        return "";
    }

}