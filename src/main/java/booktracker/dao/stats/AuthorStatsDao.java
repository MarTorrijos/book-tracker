package booktracker.dao.stats;

import org.bson.Document;

import java.util.List;

public interface AuthorStatsDao {

    List<Document> mostReadAuthor();
    List<Document> authorWithBestRatedBooks();

}