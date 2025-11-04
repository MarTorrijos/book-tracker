package booktracker.dao.author;

import booktracker.entities.Author;
import org.bson.types.ObjectId;

import java.util.List;

public interface AuthorDao {

    void insert(Author author);
    void update(ObjectId id, Author author);

    Author findByFullName(String name, String surname);
    List<Author> findBySurname(String surname);
    boolean existsByFullName(String name, String surname);

}