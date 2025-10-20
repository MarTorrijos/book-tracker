package booktracker.dao.stats;

import java.util.List;

public interface AuthorStatsDao {

    List<String> mostReadAuthor();
    List<String> authorWithBestRatedBooks();

}