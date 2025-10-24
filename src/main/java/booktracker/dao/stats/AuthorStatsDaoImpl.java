package booktracker.dao.stats;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthorStatsDaoImpl implements AuthorStatsDao {

    private final MongoCollection<Document> collection;

    public AuthorStatsDaoImpl(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    @Override
    public List<Document> mostReadAuthor() {
        return collection.aggregate(Arrays.asList(
                Aggregates.match(Filters.eq("read", true)),
                Aggregates.group(
                        new Document("name", "$author.name")
                                .append("surname", "$author.surname"),
                        Accumulators.sum("count", 1)
                ),
                Aggregates.sort(Sorts.descending("count"))
        )).into(new ArrayList<>());
    }

    @Override
    public List<Document> authorWithBestRatedBooks() {
        return collection.aggregate(Arrays.asList(
           Aggregates.match(Filters.exists("rating")),
           Aggregates.group(
                   new Document("name", "$author.name")
                           .append("surname", "$author.surname"),
                   Accumulators.avg("avgRating", "$rating")
           ),
           Aggregates.sort(Sorts.descending("avgRating"))
        )).into(new ArrayList<>());
    }

}