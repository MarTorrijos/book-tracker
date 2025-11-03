package booktracker.unit.service.book;

import booktracker.exceptions.AuthorNotFoundException;
import booktracker.exceptions.BookNotFoundException;
import booktracker.service.book.BookQueryService;
import booktracker.testdata.BookDataProvider;
import booktracker.testdata.BookListDataProvider;
import org.bson.types.ObjectId;
import booktracker.dao.book.BookDao;
import booktracker.entities.Book;
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

    private final Book book = BookDataProvider.validBook();
    private final ObjectId id = book.getId();
    private final String title = book.getTitle();
    private final List<Book> bookList = BookListDataProvider.basicBookList();
    private final List<Book> emptyList = BookListDataProvider.emptyList();
    private final String author1 = BookListDataProvider.author1;
    private final String author2 = BookListDataProvider.author2;

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
        when(bookDaoMock.findBookByAuthor(author1)).thenReturn(bookList);

        List<Book> result = service.findByAuthor(author1);

        assertFalse(result.isEmpty());
        assertTrue(result.stream().allMatch(b -> isBookByAuthor(b, author1)));
        verify(bookDaoMock).findBookByAuthor(author1);
    }

    private boolean isBookByAuthor(Book book, String author) {
        String fullName = book.getAuthor().getName() + " " + book.getAuthor().getSurname();
        return author.equals(fullName);
    }

    @Test
    void findByAuthor_NotFound() {
        when(bookDaoMock.findBookByAuthor(author2)).thenReturn(bookList);

        assertThrows(AuthorNotFoundException.class, () -> service.findByAuthor(author2));
        verify(bookDaoMock).findBookByAuthor(author2);
    }

    @Test
    void findByAuthor_EmptyBookList() {
        when(bookDaoMock.findBookByAuthor(author1)).thenReturn(emptyList);

        List<Book> result = service.findByAuthor(author1);

        assertTrue(result.isEmpty());
        verify(bookDaoMock).findBookByAuthor(author1);
    }

}