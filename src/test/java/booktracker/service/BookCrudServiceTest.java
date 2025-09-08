package booktracker.service;

import booktracker.service.author.AuthorService;
import booktracker.testdata.DataProvider;
import org.bson.types.ObjectId;
import booktracker.dao.book.BookDao;
import booktracker.entities.Author;
import booktracker.entities.Book;
import booktracker.exceptions.BookNotFoundException;
import booktracker.service.book.BookCrudService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookCrudServiceTest {

    @Mock
    private BookDao bookDaoMock;
    @Mock
    private AuthorService authorServiceMock;
    @InjectMocks
    private BookCrudService service;

    private ObjectId id;
    private Book book;
    private Author author;

    @BeforeEach
    void setUp() {
        book = DataProvider.validBook();
        author = book.getAuthor();
        id = book.getId();
    }

    @Test
    void save_Success() {
        when(bookDaoMock.bookExistsByTitle("Dune")).thenReturn(false);

        assertDoesNotThrow(() -> service.save(book));

        verify(bookDaoMock).bookExistsByTitle("Dune");
        verify(bookDaoMock).insert(book);
        verify(authorServiceMock).save(author);
    }

    @Test
    void saveDuplicatedTitle_Fail() {
        Book duplicatedBook = DataProvider.duplicatedBook();

        when(bookDaoMock.bookExistsByTitle("Dune")).thenReturn(false);
        service.save(book);
        verify(bookDaoMock).insert(book);
        when(bookDaoMock.bookExistsByTitle("Dune")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> service.save(duplicatedBook));
    }

    @Test
    void saveNoAuthor_Fail() {
        assertThrows(NullPointerException.class, () -> service.save(DataProvider.bookWithNoAuthor()));
    }

    @Test
    void update_Success() {
        Book updatedBook = DataProvider.updatedBook(id);
        when(bookDaoMock.findBookById(id)).thenReturn(book);

        assertDoesNotThrow(() -> service.update(id, updatedBook));

        verify(bookDaoMock, times(1)).findBookById(id);
        verify(bookDaoMock, times(1)).update(id, updatedBook);
    }

    @Test
    void updateBookNotFound_Fail() {
        Book updatedBook = DataProvider.updatedBook(id);
        when(bookDaoMock.findBookById(id)).thenReturn(null);

        assertThrows(BookNotFoundException.class, () -> service.update(id, updatedBook));

        verify(bookDaoMock, never()).update(any(), any());
    }

    @Test
    void delete_Success() {
        when(bookDaoMock.findBookById(id)).thenReturn(book);

        assertDoesNotThrow(() -> service.delete(id));

        verify(bookDaoMock, times(1)).findBookById(id);
        verify(bookDaoMock, times(1)).delete(id);
    }

    @Test
    void deleteBookNotFound_Fail() {
        when(bookDaoMock.findBookById(id)).thenReturn(null);

        assertThrows(BookNotFoundException.class, () -> service.delete(id));
        verify(bookDaoMock, never()).delete(any());
    }

}