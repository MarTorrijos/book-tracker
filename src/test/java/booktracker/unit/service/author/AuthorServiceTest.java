package booktracker.unit.service.author;

import booktracker.dao.author.AuthorDao;
import booktracker.entities.Author;
import booktracker.exceptions.AuthorNotFoundException;
import booktracker.exceptions.DuplicatedAuthorException;
import booktracker.service.author.AuthorService;
import booktracker.testdata.AuthorDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorDao authorDaoMock;

    @InjectMocks
    private AuthorService service;

    private final Author author = AuthorDataProvider.validAuthor();
    private final List<Author> authorList = AuthorDataProvider.authorList();
    private Author newData;
    private Author updatedAuthor;

    @BeforeEach
    void setUp() {
        newData = AuthorDataProvider.newAuthorData("Thomas", author);
        updatedAuthor = AuthorDataProvider.updatedAuthorFrom(author, newData);
    }

    @Test
    @DisplayName("Saves an author given a valid one")
    void saveAuthor_Success() {
        when(authorDaoMock.existsByFullName(author.getName(), author.getSurname())).thenReturn(false);
        doNothing().when(authorDaoMock).insert(author);

        Author saved = assertDoesNotThrow(() -> service.saveAuthor(author));

        assertEquals(author, saved);
        verify(authorDaoMock).existsByFullName(author.getName(), author.getSurname());
        verify(authorDaoMock).insert(author);
    }

    @Test
    @DisplayName("Can't save an author given an already existing one")
    void saveAuthor_ExistingAuthor_Fail() {
        when(authorDaoMock.existsByFullName(author.getName(), author.getSurname())).thenReturn(false);
        service.saveAuthor(author);
        verify(authorDaoMock).insert(author);
        when(authorDaoMock.existsByFullName(author.getName(), author.getSurname())).thenReturn(true);

        assertThrows(DuplicatedAuthorException.class, () -> service.saveAuthor(author));

        verify(authorDaoMock, times(1)).insert(any());
    }

    @Test
    @DisplayName("Updates an author given it exists before")
    void updateAuthor_Success() {
        when(authorDaoMock.findByFullName(author.getName(), author.getSurname()))
                .thenReturn(author)
                .thenReturn(updatedAuthor);

        Author result = service.updateAuthor(author.getName(), author.getSurname(), newData);

        assertEquals(updatedAuthor.getName(), result.getName());
        assertEquals(updatedAuthor.getSurname(), result.getSurname());
        assertEquals(updatedAuthor.getId(), result.getId());
        verify(authorDaoMock).update(author.getId(), newData);
        verify(authorDaoMock, times(2)).findByFullName(author.getName(), author.getSurname());
    }

    @Test
    @DisplayName("Can't update an author since it doesn't exist")
    void updateAuthor_DoesntExist_Fail() {
        when(authorDaoMock.findByFullName(author.getName(), author.getSurname()))
                .thenReturn(null);

        assertThrows(AuthorNotFoundException.class,
                () -> service.updateAuthor(author.getName(), author.getSurname(), newData));

        verify(authorDaoMock, never()).update(any(), any());
    }

    @Test
    @DisplayName("Finds an author by their full name given an already existing one")
    void findAuthorByFullName_Success() {
        when(authorDaoMock.findByFullName(author.getName(), author.getSurname())).thenReturn(author);

        Author result = service.findAuthorByFullName(author.getName(), author.getSurname());

        assertEquals(author, result);
        verify(authorDaoMock).findByFullName(author.getName(), author.getSurname());
    }

    @Test
    @DisplayName("Can't find an author by their full name since it doesn't exist")
    void findAuthorByFullName_DoesntExist_Fail() {
        when(authorDaoMock.findByFullName(author.getName(), author.getSurname())).thenReturn(null);

        assertThrows(AuthorNotFoundException.class, () -> service.findAuthorByFullName(author.getName(), author.getSurname()));

        verify(authorDaoMock).findByFullName(author.getName(), author.getSurname());
    }

    @Test
    @DisplayName("Finds authors by their surname, returning a list of matching authors")
    void findAuthorBySurname_Success() {
        when(authorDaoMock.findBySurname(author.getSurname())).thenReturn(authorList);

        List<Author> result = service.findAuthorBySurname(author.getSurname());

        assertEquals(authorList, result);
        assertTrue(result.stream().allMatch(a -> a.getSurname().equals(author.getSurname())));
        verify(authorDaoMock).findBySurname(author.getSurname());
    }

    @Test
    @DisplayName("Can't find an author by their surname since it doesn't exist")
    void findAuthorBySurname_DoesntExist_Fail() {
        when(authorDaoMock.findBySurname(AuthorDataProvider.OTHER_AUTHOR_SURNAME)).thenReturn(null);

        assertThrows(AuthorNotFoundException.class,
                () -> service.findAuthorBySurname(AuthorDataProvider.OTHER_AUTHOR_SURNAME));

        verify(authorDaoMock).findBySurname(AuthorDataProvider.OTHER_AUTHOR_SURNAME);
    }

}