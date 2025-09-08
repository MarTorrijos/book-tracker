package booktracker.dao.book;

import org.bson.types.ObjectId;
import booktracker.entities.Book;

import java.util.List;

public interface BookDao {

    void insert(Book book);
    void update(ObjectId id, Book book);
    void delete(ObjectId id);

    List<Book> findAllBooks();
    Book findBookById(ObjectId id);
    Book findBookByTitle(String title);
    List<Book> findBookByAuthor(String author);
    boolean bookExistsByTitle(String title);

}