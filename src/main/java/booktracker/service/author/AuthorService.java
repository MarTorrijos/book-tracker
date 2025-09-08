package booktracker.service.author;

import booktracker.dao.author.AuthorDao;
import booktracker.entities.Author;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthorService {

    private final AuthorDao authorDao;
    private static final Logger logger = LogManager.getLogger(AuthorService.class.getName());

    public AuthorService(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public Author findByName(String name) {
        return authorDao.findByName(name);
    }

    public Author save(Author author) {
        return authorDao.insert(author);
    }

    // update authors

}