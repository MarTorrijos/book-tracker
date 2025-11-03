package booktracker.testdata;

import booktracker.entities.Author;
import booktracker.entities.Book;
import booktracker.entities.Review;
import org.bson.types.ObjectId;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class BookListDataProvider {

    public static String author1 = "Ursula K. Le Guin";
    public static String author2 = "Stephenie Meyer";

    public static List<Book> emptyList() {
        return new ArrayList<>();
    }

    public static List<Book> basicBookList() {
        return List.of(
                Book.builder()
                        .id(new ObjectId())
                        .title("A Wizard of Earthsea")
                        .author(Author.builder()
                                .name("Ursula")
                                .surname("K. Le Guin")
                                .build())
                        .build(),
                Book.builder()
                        .id(new ObjectId())
                        .title("The Left Hand of Darkness")
                        .author(Author.builder()
                                .name("Ursula")
                                .surname("K. Le Guin")
                                .build())
                        .build(),
                Book.builder()
                        .id(new ObjectId())
                        .title("No Logo")
                        .author(Author.builder()
                                .name("Naomi")
                                .surname("Klein")
                                .build())
                        .build()
        );
    }

    public static List<Book> complexBookList() {
        return List.of(
                Book.builder()
                        .id(new ObjectId())
                        .title("The Lord of the Rings")
                        .author(Author.builder()
                                .name("J.R.R.")
                                .surname("Tolkien")
                                .build())
                        .read(true)
                        .review(new Review(2021, 4.5f, "So good", true))
                        .build(),
                Book.builder()
                        .id(new ObjectId())
                        .title("Dracula")
                        .author(Author.builder()
                                .name("Bram")
                                .surname("Stoker")
                                .build())
                        .read(false)
                        .build(),
                Book.builder()
                        .id(new ObjectId())
                        .title("Assassin's Apprentice")
                        .author(Author.builder()
                                .name("Robin")
                                .surname("Hobb")
                                .build())
                        .read(true)
                        .review(new Review(2025, 4.5f, "So good", true))
                        .build(),
                Book.builder()
                        .id(new ObjectId())
                        .title("Where the wild things are")
                        .author(Author.builder()
                                .name("Maurice")
                                .surname("Sendak")
                                .build())
                        .read(false)
                        .build()
        );
    }

    public static List<Book> bookStatsList() {
        int currentYear = Year.now().getValue();

        return List.of(
                Book.builder()
                        .id(new ObjectId())
                        .title("Pride and Prejudice")
                        .author(Author.builder()
                                .name("Jane")
                                .surname("Austen")
                                .build())
                        .read(true)
                        .review(new Review(currentYear, 3f, "meh", false))
                        .build(),
                Book.builder()
                        .id(new ObjectId())
                        .title("Steppenwolf")
                        .author(Author.builder()
                                .name("Hermann")
                                .surname("Hesse")
                                .build())
                        .read(true)
                        .review(new Review(2021, 4.1f, "", true))
                        .build()
        );
    }

    public static List<Book> authorStatsList() {
        return List.of(
                Book.builder()
                        .id(new ObjectId())
                        .title("Dune")
                        .author(Author.builder()
                                .name("Frank")
                                .surname("Herbert")
                                .build())
                        .read(true)
                        .review(new Review(2019, 4.75f, "Could have had more sandworms", true))
                        .build(),
                Book.builder()
                        .id(new ObjectId())
                        .title("Dune Messiah")
                        .author(Author.builder()
                                .name("Frank")
                                .surname("Herbert")
                                .build())
                        .read(true)
                        .review(new Review(2019, 4.5f, "I wish there was a human/sandworm hybrid...", true))
                        .build(),
                Book.builder()
                        .id(new ObjectId())
                        .title("Perfume: The story of a murderer")
                        .author(Author.builder()
                                .name("Patrick")
                                .surname("Süskind")
                                .build())
                        .read(true)
                        .review(new Review(2019, 5f, "", true))
                        .build()
        );
    }

}