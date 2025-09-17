package booktracker.testdata;

import booktracker.entities.Author;
import booktracker.entities.Book;
import booktracker.entities.Review;
import org.bson.types.ObjectId;

import java.time.Year;
import java.util.List;

public class BookListDataProvider {

    public static List<Book> basicBookList() {
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

    public static List<Book> complexBookList() {
        return List.of(
                Book.builder()
                        .id(new ObjectId())
                        .title("The Lord of the Rings")
                        .author(Author.builder().name("J.R.R. Tolkien").build())
                        .read(true)
                        .review(new Review(2021, 4.5f, "So good", true))
                        .build(),
                Book.builder()
                        .id(new ObjectId())
                        .title("Dracula")
                        .author(Author.builder().name("Bram Stoker").build())
                        .read(false)
                        .build(),
                Book.builder()
                        .id(new ObjectId())
                        .title("Assassin's Apprentice")
                        .author(Author.builder().name("Robin Hobb").build())
                        .read(true)
                        .review(new Review(2025, 4.5f, "So good", true))
                        .build(),
                Book.builder()
                        .id(new ObjectId())
                        .title("Where the wild things are")
                        .author(Author.builder().name("Maurice Sendak").build())
                        .read(false)
                        .build()
        );
    }

    public static List<Book> statsBookList() {
        int currentYear = Year.now().getValue();

        return List.of(
                Book.builder()
                        .id(new ObjectId())
                        .title("Pride and Prejudice")
                        .author(Author.builder().name("Jane Austen").build())
                        .read(true)
                        .review(new Review(currentYear, 3f, "meh", false))
                        .build(),
                Book.builder()
                        .id(new ObjectId())
                        .title("Steppenwolf")
                        .author(Author.builder().name("Hermann Hesse").build())
                        .read(true)
                        .review(new Review(2021, 4.1f, "", true))
                        .build()
        );
    }

}