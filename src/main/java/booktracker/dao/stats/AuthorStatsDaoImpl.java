package booktracker.dao.stats;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Aggregates.match;

public class AuthorStatsDaoImpl implements AuthorStatsDao {

    private final MongoCollection<Document> collection;

    public AuthorStatsDaoImpl(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    @Override
    public List<String> mostReadAuthor() {
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                match(Filters.eq("read", true)),
                new Document("$group", new Document("_id", "$author")
                        .append("count", new Document("$sum", 1))),
                new Document("$sort", new Document("count", -1))
        ));

        List<String> topAuthors = new ArrayList<>();
        int maxCount = -1;

        for (Document doc : result) {
            int count = doc.getInteger("count");
            String author = doc.getString("_id");

            if (maxCount == -1) {
                maxCount = count;
                topAuthors.add(author);
            } else if (count == maxCount) {
                topAuthors.add(author);
            } else {
                break;
            }
        }

        return topAuthors;
    }

    @Override
    public List<String> authorWithBestRatedBooks() {
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                match(Filters.exists("rating")),
                new Document("$group", new Document("_id", "$author")
                        .append("avgRating", new Document("$avg", "$rating"))),
                new Document("$sort", new Document("avgRating", -1))
        ));

        List<String> topAuthors = new ArrayList<>();
        double maxAvgRating = -1;

        for (Document doc : result) {
            double avgRating = doc.getDouble("avgRating");
            String author = doc.getString("_id");

            if (maxAvgRating == -1) {
                maxAvgRating = avgRating;
                topAuthors.add(author);
            } else if (Double.compare(avgRating, maxAvgRating) == 0) {
                topAuthors.add(author);
            } else {
                break;
            }
        }

        return topAuthors;
    }

}