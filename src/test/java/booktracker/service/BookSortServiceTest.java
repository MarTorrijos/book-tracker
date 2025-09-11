package booktracker.service;

import booktracker.dao.book.BookDao;
import booktracker.service.book.BookSortService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BookSortServiceTest {

    @Mock
    private BookDao bookDaoMock;

    @InjectMocks
    private BookSortService service;

    @BeforeEach
    void setUp() {

    }

    @Test
    void sortByAuthorAsc() {

    }

    @Test
    void sortByAuthorDesc() {

    }

    @Test
    void sortByTitleAsc() {

    }

    @Test
    void sortByTitleDesc() {

    }

    @Test
    void sortByReadYearAsc() {

    }

    @Test
    void sortByReadYearDesc() {

    }

}