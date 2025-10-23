package booktracker.dao.stats;

import booktracker.exceptions.DataAccessException;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.time.Year;

public class BookStatsDaoImpl implements BookStatsDao {

    private final MongoCollection<Document> collection;
    private final int CURRENT_YEAR = Year.now().getValue();

    public BookStatsDaoImpl(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    @Override
    public int countBooksReadThisYear() {
        try {
            Document filter = new Document("review.readIn", CURRENT_YEAR)
                    .append("read", true);
            return (int) collection.countDocuments(filter);
        } catch (MongoException e) {
            throw new DataAccessException("Failed to count books read this year", e);
        }
    }

    @Override
    public int countBooksReadInGivenYear(int givenYear) {
        try {
            Document filter = new Document("review.readIn", givenYear)
                    .append("read", true);
            return (int) collection.countDocuments(filter);
        } catch (MongoException e) {
            throw new DataAccessException("Failed to count books in year " + givenYear, e);
        }
    }

    @Override
    public int countTotalBooksRead() {
        try {
            Document filter = new Document("read", true);
            return (int) collection.countDocuments(filter);
        } catch (MongoException e) {
            throw new DataAccessException("Failed to count total books read", e);
        }
    }

}