package booktracker.service.stats;

import booktracker.dao.stats.BookStatsDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static booktracker.log.BookStatsLogMessages.*;

public class BookStatsService {

    private final BookStatsDao bookStatsDao;
    private static final Logger logger = LogManager.getLogger(BookStatsService.class.getName());

    public BookStatsService(BookStatsDao bookStatsDao) {
        this.bookStatsDao = bookStatsDao;
    }

    public int countBooksReadThisYear() {
        int count = bookStatsDao.countBooksReadThisYear();
        logger.info(COUNT_BOOKS_READ_THIS_YEAR, count);
        return count;
    }

    public int countBooksReadInGivenYear(int year) {
        int count = bookStatsDao.countBooksReadThisYear();
        logger.info(COUNT_BOOKS_READ_IN_YEAR, year, count);
        return count;
    }

    public int countTotalBooksRead() {
        int count = bookStatsDao.countTotalBooksRead();
        logger.info(COUNT_TOTAL_BOOKS_READ, count);
        return count;
    }

}