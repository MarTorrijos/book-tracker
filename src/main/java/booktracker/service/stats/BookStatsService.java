package booktracker.service.stats;

import booktracker.dao.book.BookDao;
import booktracker.entities.Book;

public class BookStatsService {

    private final BookDao bookDao;

    public BookStatsService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public int countBooksReadThisYear() {

    }

    public int countBooksReadInGivenYear(int year) {

    }

    public int countTotalBooksRead() {

    }

    // más?

    private Integer getReadYear(Book book) {
        return book.getReview() != null ? book.getReview().getReadIn() : null;
    }

}