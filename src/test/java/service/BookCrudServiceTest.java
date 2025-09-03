package service;

import org.bson.types.ObjectId;
import org.example.dao.author.AuthorDao;
import org.example.dao.book.BookDao;
import org.example.entities.Author;
import org.example.entities.Book;
import org.example.exceptions.BookNotFoundException;
import org.example.service.book.BookCrudService;
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
    private AuthorDao authorDaoMock;
    @InjectMocks
    private BookCrudService service;

    private ObjectId id;
    private Book book;
    private Author author;

    @BeforeEach
    void setUp() {
        id = new ObjectId();

        author = new Author();
        author.setName("Frank Herbert");

        book = new Book();
        book.setId(id);
        book.setTitle("Dune");
        book.setAuthor(author);
    }

    @Test
    void save_Success() {
        when(bookDaoMock.bookExistsByTitle("Dune")).thenReturn(false);

        assertDoesNotThrow(() -> service.save(book));

        verify(bookDaoMock).bookExistsByTitle("Dune");
        verify(bookDaoMock).insert(book);
    }

    @Test
    void saveDuplicatedTitle_Fail() {
        Book duplicatedBook = new Book();
        duplicatedBook.setTitle("Dune");
        duplicatedBook.setAuthor(author);

        when(bookDaoMock.bookExistsByTitle("Dune")).thenReturn(false);
        service.save(book);
        verify(bookDaoMock).insert(book);
        when(bookDaoMock.bookExistsByTitle("Dune")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> service.save(duplicatedBook));
    }

    @Test
    void update_Success() {
        Book updatedBook = new Book();
        updatedBook.setId(id);
        updatedBook.setTitle("Dune Messiah");
        updatedBook.setAuthor(author);
        when(bookDaoMock.findBookById(id)).thenReturn(book);

        assertDoesNotThrow(() -> service.update(id, updatedBook));

        verify(bookDaoMock, times(1)).findBookById(id);
        verify(bookDaoMock, times(1)).update(id, updatedBook);
    }

    @Test
    void updateBookNotFound_Fail() {
        Book updatedBook = new Book();
        updatedBook.setTitle("Dune Messiah");
        updatedBook.setAuthor(author);
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