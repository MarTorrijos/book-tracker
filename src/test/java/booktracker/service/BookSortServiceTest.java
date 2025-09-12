package booktracker.service;

import booktracker.dao.book.BookDao;
import booktracker.entities.Book;
import booktracker.service.book.BookSortService;
import booktracker.testdata.BookListDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Comparator;
import java.util.List;

import static booktracker.service.book.BookSortCriteria.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookSortServiceTest {

    @Mock
    private BookDao bookDaoMock;

    @InjectMocks
    private BookSortService service;

    @BeforeEach
    void setUp() {
        when(bookDaoMock.findAllBooks()).thenReturn(BookListDataProvider.bookListToSort());
    }

    @Test
    void sortNotRead_ByAuthorAsc() {
        List<Book> sorted = service.getSortedBooks(false, AUTHOR_ASC);

        assertThat(sorted)
                .isNotEmpty()
                .extracting(Book::getTitle)
                .isSorted();

        assertThat(sorted.getFirst().getAuthor().getName()).isEqualTo("Bram Stoker");
    }

    @Test
    void sortNotRead_ByAuthorDesc() {
        List<Book> sorted = service.getSortedBooks(false, AUTHOR_DESC);

        assertThat(sorted)
                .isNotEmpty()
                .extracting(Book::getTitle)
                .isSortedAccordingTo(Comparator.reverseOrder());

        assertThat(sorted.getFirst().getAuthor().getName()).isEqualTo("Maurice Sendak");
    }

    @Test
    void sortAll_ByTitleAsc() {
        List<Book> sorted = service.getSortedBooks(null, TITLE_ASC);

        assertThat(sorted)
                .isNotEmpty()
                .extracting(Book::getTitle)
                .isSorted();

        assertThat(sorted.getFirst().getTitle()).isEqualTo("Assassin's Apprentice");
    }

    @Test
    void sortAll_ByTitleDesc() {
        List<Book> sorted = service.getSortedBooks(null, TITLE_DESC);

        assertThat(sorted)
                .isNotEmpty()
                .extracting(Book::getTitle)
                .isSortedAccordingTo(Comparator.reverseOrder());

        assertThat(sorted.getFirst().getTitle()).isEqualTo("Where the wild things are");
    }

    @Test
    void sortRead_ByReadYearAsc() {
        List<Book> sorted = service.getSortedBooks(true, READ_YEAR_ASC);

        assertThat(sorted)
                .isNotEmpty()
                .extracting(book -> book.getReview().getReadIn())
                .isSorted();

        assertThat(sorted.getFirst().getReview().getReadIn()).isEqualTo(2021);
    }

    @Test
    void sortRead_ByReadYearDesc() {
        List<Book> sorted = service.getSortedBooks(true, READ_YEAR_DESC);

        assertThat(sorted)
                .isNotEmpty()
                .extracting(book -> book.getReview().getReadIn())
                .isSortedAccordingTo(Comparator.reverseOrder());

        assertThat(sorted.getFirst().getReview().getReadIn()).isEqualTo(2025);
    }

}