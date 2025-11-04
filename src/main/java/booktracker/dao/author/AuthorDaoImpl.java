package booktracker.dao.author;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import booktracker.entities.Author;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class AuthorDaoImpl implements AuthorDao {

    private final MongoCollection<Document> collection;
    private final ObjectMapper mapper;

    public AuthorDaoImpl(MongoCollection<Document> collection, ObjectMapper mapper) {
        this.collection = collection;
        this.mapper = mapper;
    }

    @Override
    public void insert(Author author) {
        Document doc = new Document(
                "name", author.getName())
                .append("surname", author.getSurname());

        collection.insertOne(doc);
        author.setId(doc.getObjectId("_id"));
    }

    @Override
    public void update(ObjectId id, Author author) {
        Bson filter = eq("_id", id);
        Document doc = toDocument(author);
        doc.remove("_id");
        collection.findOneAndUpdate(filter, new Document("$set", doc));
    }

    @Override
    public Author findByFullName(String name, String surname) {
        Document doc = collection.find(and(eq("name", name), eq("surname", surname))).first();
        return doc != null ? mapper.convertValue(doc, Author.class) : null;
    }

    @Override
    public List<Author> findBySurname(String surname) {
        List<Author> authors = new ArrayList<>();

        for (Document doc : collection.find(eq("surname", surname))) {
            Author author = mapper.convertValue(doc, Author.class);
            authors.add(author);
        }

        return authors;
    }

    @Override
    public boolean existsByFullName(String name, String surname) {
        Document doc = collection.find(and(eq("name", name), eq("surname", surname))).first();
        return doc != null;
    }

    private Document toDocument(Author author) {
        Map<String, Object> map = mapper.convertValue(author, new TypeReference<>() {});
        return new Document(map);
    }

}