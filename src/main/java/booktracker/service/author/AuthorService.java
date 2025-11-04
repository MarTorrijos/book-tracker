package booktracker.service.author;

import booktracker.dao.author.AuthorDao;
import booktracker.entities.Author;
import booktracker.exceptions.AuthorNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static booktracker.service.author.logs.AuthorLogMessages.*;

public class AuthorService {

    private final AuthorDao authorDao;
    private static final Logger logger = LogManager.getLogger(AuthorService.class.getName());

    public AuthorService(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public Author saveAuthor(Author author) {
        if (authorExistsByFullName(author.getName(), author.getSurname())) {
            logger.warn(SAVE_AUTHOR_FAIL_LOG, author);
            throw new IllegalArgumentException("Author with that name already exists");
        }

        authorDao.insert(author);
        logger.info(SAVE_AUTHOR_SUCCESS_LOG, author);
        return author;
    }

    public Author updateAuthor(String name, String surname, Author newData) {
        Author existing = findAuthorByFullName(name, surname);

        newData.setId(existing.getId());
        authorDao.update(existing.getId(), newData);

        return authorDao.findByFullName(name, surname);
    }

    public Author findAuthorByFullName(String name, String surname) {
        Author author = authorDao.findByFullName(name, surname);

        if (author == null) {
            logger.warn(FIND_AUTHOR_BY_FULL_NAME_FAIL_LOG, name, surname);
            throw new AuthorNotFoundException(String.format(
                    "Author named %s not found", name + " " + surname));
        }

        logger.info(FIND_AUTHOR_BY_FULL_NAME_SUCCESS_LOG, name, surname);
        return author;
    }

    public List<Author> findAuthorBySurname(String surname) {
        List<Author> authorList = authorDao.findBySurname(surname);

        if (authorList == null || authorList.isEmpty()) {
            logger.warn(FIND_AUTHOR_BY_SURNAME_FAIL_LOG, surname);
            throw new AuthorNotFoundException(
                    String.format("No authors found with surname %s", surname));
        }

        logger.info(FIND_AUTHOR_BY_SURNAME_SUCCESS_LOG, authorList);
        return authorList;
    }

    public boolean authorExistsByFullName(String name, String surname) {
        return authorDao.existsByFullName(name, surname);
    }

}