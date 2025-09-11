package booktracker.testdata;

import booktracker.entities.Author;
import booktracker.entities.Book;
import booktracker.entities.Review;
import org.bson.types.ObjectId;

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

    public static List<Book> bookListToSort() {
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
                        .review(new Review(2021, 4.5f, "So good", true))
                        .build()
        );
    }

}