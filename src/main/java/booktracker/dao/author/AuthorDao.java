package booktracker.dao.author;

import booktracker.entities.Author;

public interface AuthorDao {

    Author findByName(String name);
    Author insert(Author author);

}