package booktracker.dao.stats;

public interface BookStatsDao {

    int countBooksReadThisYear();
    int countBooksReadInGivenYear(int year);
    int countTotalBooksRead();

}