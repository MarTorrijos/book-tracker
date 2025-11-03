package booktracker.service.stats;

import booktracker.dao.stats.AuthorStatsDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static booktracker.service.stats.logs.AuthorStatsLogMessages.*;

public class AuthorStatsService {

    private final AuthorStatsDao authorStatsDao;
    private static final Logger logger = LogManager.getLogger(AuthorStatsService.class.getName());

    public AuthorStatsService(AuthorStatsDao authorStatsDao) {
        this.authorStatsDao = authorStatsDao;
    }

    public List<String> mostReadAuthor() {
        List<Document> docs = authorStatsDao.mostReadAuthor();

        if (docs.isEmpty()) {
            logger.info(MOST_READ_AUTHOR_FAIL_LOG);
        }

        return filterTopAuthorsByCount(docs);
    }

    public List<String> authorWithBestRatedBooks() {
        List<Document> docs = authorStatsDao.authorWithBestRatedBooks();

        if (docs.isEmpty()) {
            logger.info(BEST_RATED_AUTHOR_FAIL_LOG);
        }

        return filterTopAuthorsByAvgRating(docs);
    }

    private List<String> filterTopAuthorsByCount(List<Document> docs) {
        List<String> topAuthors = new ArrayList<>();
        int maxCount = -1;

        for (Document doc : docs) {
            int count = doc.getInteger("count");

            Document authorDoc = doc.get("_id", Document.class);
            String authorFullName = authorDoc.getString("name") + " " + authorDoc.getString("surname");

            if (maxCount == -1) {
                maxCount = count;
                topAuthors.add(authorFullName);
            } else if (count == maxCount) {
                topAuthors.add(authorFullName);
            } else {
                break;
            }
        }
        return topAuthors;
    }

    private List<String> filterTopAuthorsByAvgRating(List<Document> docs) {
        List<String> topAuthors = new ArrayList<>();
        double maxAvgRating = -1;

        for (Document doc : docs) {
            double avgRating = doc.getDouble("avgRating");

            Document authorDoc = doc.get("_id", Document.class);
            String authorFullName = authorDoc.getString("name") + " " + authorDoc.getString("surname");

            if (maxAvgRating == -1) {
                maxAvgRating = avgRating;
                topAuthors.add(authorFullName);
            } else if (Double.compare(avgRating, maxAvgRating) == 0) {
                topAuthors.add(authorFullName);
            } else {
                break;
            }
        }
        return topAuthors;
    }

}