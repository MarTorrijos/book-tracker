package org.example.dao.author;

import org.example.entities.Author;

public interface AuthorDao {

    Author findByName(String name);
    Author insert(Author author);

}