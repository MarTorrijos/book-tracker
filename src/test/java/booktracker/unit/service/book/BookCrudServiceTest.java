package booktracker.unit.service.book;

import booktracker.service.author.AuthorService;
import booktracker.service.book.BookCrudService;
import booktracker.testdata.BookDataProvider;
import org.bson.types.ObjectId;
import booktracker.dao.book.BookDao;
import booktracker.entities.Author;
import booktracker.entities.Book;
import booktracker.exceptions.BookNotFoundException;
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
    private Book duplicatedBook;
    private Author author;

    @BeforeEach
    void setUp() {
        book = BookDataProvider.validBook();
        duplicatedBook = BookDataProvider.duplicatedBook();
        author = book.getAuthor();
        id = book.getId();
    }

    @Test
    void save_Success() {
        when(bookDaoMock.bookExistsByTitle(book.getTitle())).thenReturn(false);

        assertDoesNotThrow(() -> service.save(book));
        verify(bookDaoMock).bookExistsByTitle(book.getTitle());
        verify(bookDaoMock).insert(book);
        verify(authorServiceMock).save(author);
    }

    @Test
    void save_DuplicatedTitle_Fail() {
        when(bookDaoMock.bookExistsByTitle(book.getTitle())).thenReturn(false);
        service.save(book);
        verify(bookDaoMock).insert(book);

        when(bookDaoMock.bookExistsByTitle(duplicatedBook.getTitle())).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> service.save(duplicatedBook));

        verify(bookDaoMock, times(1)).insert(any());
    }

    @Test
    void save_NoAuthor_Fail() {
        assertThrows(NullPointerException.class, () -> service.save(BookDataProvider.bookWithNoAuthor()));
    }

    @Test
    void update_Success() {
        Book updatedBook = BookDataProvider.updatedBook(id);
        when(bookDaoMock.findBookById(id)).thenReturn(book);

        assertDoesNotThrow(() -> service.update(id, updatedBook));

        verify(bookDaoMock).findBookById(id);
        verify(bookDaoMock).update(id, updatedBook);
    }

    @Test
    void update_BookNotFound_Fail() {
        Book updatedBook = BookDataProvider.updatedBook(id);
        when(bookDaoMock.findBookById(id)).thenReturn(null);

        assertThrows(BookNotFoundException.class, () -> service.update(id, updatedBook));
        verify(bookDaoMock, never()).update(any(), any());
    }

    @Test
    void delete_Success() {
        when(bookDaoMock.findBookById(id)).thenReturn(book);

        assertDoesNotThrow(() -> service.delete(id));

        verify(bookDaoMock).findBookById(id);
        verify(bookDaoMock).delete(id);
    }

    @Test
    void delete_BookNotFound_Fail() {
        when(bookDaoMock.findBookById(id)).thenReturn(null);

        assertThrows(BookNotFoundException.class, () -> service.delete(id));
        verify(bookDaoMock, never()).delete(any());
    }

}