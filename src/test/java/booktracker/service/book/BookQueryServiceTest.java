package booktracker.service.book;

import booktracker.exceptions.BookNotFoundException;
import booktracker.testdata.BookDataProvider;
import booktracker.testdata.BookListDataProvider;
import org.bson.types.ObjectId;
import booktracker.dao.book.BookDao;
import booktracker.entities.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookQueryServiceTest {

    @Mock
    private BookDao bookDaoMock;

    @InjectMocks
    private BookQueryService service;

    private ObjectId id;
    private Book book;
    private String title;
    private List<Book> bookList;

    @BeforeEach
    void setUp() {
        book = BookDataProvider.validBook();
        title = book.getTitle();
        id = book.getId();
        bookList = BookListDataProvider.basicBookList();
    }

    @Test
    void findAll_Success() {
        when(bookDaoMock.findAllBooks()).thenReturn(bookList);
        List<Book> result = service.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(bookDaoMock).findAllBooks();
    }

    @Test
    void findById_Success() {
        when(bookDaoMock.findBookById(id)).thenReturn(book);
        Book foundBook = service.findById(id);

        assertNotNull(foundBook);
        assertEquals("Dune", foundBook.getTitle());
        verify(bookDaoMock).findBookById(id);
    }

    @Test
    void findById_NotFound() {
        ObjectId wrongId = new ObjectId();
        when(bookDaoMock.findBookById(wrongId)).thenReturn(null);

        assertThrows(BookNotFoundException.class, () -> service.findById(wrongId));
        verify(bookDaoMock).findBookById(wrongId);
    }

    @Test
    void findByTitle_Success() {
        when(bookDaoMock.findBookByTitle(title)).thenReturn(book);

        assertDoesNotThrow(() -> service.findByTitle(title));
        verify(bookDaoMock).findBookByTitle(title);
    }

    @Test
    void findByTitle_NotFound() {
        when(bookDaoMock.findBookByTitle(title)).thenReturn(null);

        assertThrows(BookNotFoundException.class, () -> service.findByTitle(title));
        verify(bookDaoMock).findBookByTitle(title);
    }

    @Test
    void findByAuthor_Success() {
        String authorList = "Ursula K. Le Guin";
        when(bookDaoMock.findBookByAuthor(authorList)).thenReturn(bookList);

        List<Book> result = service.findByAuthor(authorList);

        assertFalse(result.isEmpty());
        assertTrue(result.stream().
                allMatch(b -> authorList.equals(b.getAuthor().getName())));
        verify(bookDaoMock).findBookByAuthor(authorList);
    }

}