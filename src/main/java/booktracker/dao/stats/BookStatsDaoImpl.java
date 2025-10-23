package booktracker.dao.stats;

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
        Document filter = new Document("review.readIn", CURRENT_YEAR)
                .append("read", true);
        return (int) collection.countDocuments(filter);
    }

    @Override
    public int countBooksReadInGivenYear(int givenYear) {
        Document filter = new Document("review.readIn", givenYear)
                .append("read", true);
        return (int) collection.countDocuments(filter);
    }

    @Override
    public int countTotalBooksRead() {
        Document filter = new Document("read", true);
        return (int) collection.countDocuments(filter);
    }

}