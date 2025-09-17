package booktracker.service.stats;

import booktracker.dao.stats.BookStatsDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static booktracker.log.BookStatsLogMessages.COUNT_BOOKS_READ_IN_YEAR;

public class BookStatsService {

    private final BookStatsDao bookStatsDao;
    private static final Logger logger = LogManager.getLogger(BookStatsService.class.getName());

    public BookStatsService(BookStatsDao bookStatsDao) {
        this.bookStatsDao = bookStatsDao;
    }

    public int countBooksReadThisYear() {
        return bookStatsDao.countBooksReadThisYear();
    }

    public int countBooksReadInGivenYear(int year) {
        logger.debug(COUNT_BOOKS_READ_IN_YEAR, year);
        return bookStatsDao.countBooksReadInGivenYear(year);
    }

    public int countTotalBooksRead() {
        return bookStatsDao.countTotalBooksRead();
    }

}