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
    public List<Document> mostReadAuthor() {
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                match(Filters.eq("read", true)),
                new Document("$group", new Document("_id", "$author")
                        .append("count", new Document("$sum", 1))),
                new Document("$sort", new Document("count", -1))
        ));
        return result.into(new ArrayList<>());
    }

    @Override
    public List<Document> authorWithBestRatedBooks() {
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                match(Filters.exists("rating")),
                new Document("$group", new Document("_id", "$author")
                        .append("avgRating", new Document("$avg", "$rating"))),
                new Document("$sort", new Document("avgRating", -1))
        ));
        return result.into(new ArrayList<>());
    }

}