package org.example.dao.book;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.example.entities.Book;

import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

public class BookDaoImpl implements BookDao {

    private final MongoCollection<Document> collection;
    private final ObjectMapper mapper;

    public BookDaoImpl(MongoCollection<Document> collection, ObjectMapper mapper) {
        this.collection = collection;
        this.mapper = mapper;
    }

    @Override
    public void insert(Book book) {
        Document doc = toDocument(book);
        collection.insertOne(doc);
    }

    @Override
    public void update(ObjectId id, Book book) {
        Bson filter = eq("_id", id);
        Document doc = toDocument(book);
        doc.remove("_id");
        collection.findOneAndUpdate(filter, new Document("$set", doc));
    }

    @Override
    public void delete(ObjectId id) {
        collection.findOneAndDelete(eq("_id", id));
    }

    @Override
    public List<Book> findAll() {
        return collection.find()
                .into(new java.util.ArrayList<>())
                .stream()
                .map(doc -> mapper.convertValue(doc, Book.class))
                .toList();
    }

    @Override
    public Book findById(ObjectId id) {
        Document doc = collection.find(eq("_id", id)).first();
        return doc != null ? mapper.convertValue(doc, Book.class) : null;
    }

    @Override
    public Book findByTitle(String title) {
        Document doc = collection.find(eq("title", title)).first();
        return doc != null ? mapper.convertValue(doc, Book.class) : null;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        List<Document> docs = collection.find(eq("author", author)).into(new java.util.ArrayList<>());
        return docs.stream().map(d -> mapper.convertValue(d, Book.class)).toList();
    }

    @Override
    public boolean existsByTitle(String title) {
        return collection.find(eq("title", title)).first() != null;
    }

    private Document toDocument(Book book) {
        Map<String, Object> map = mapper.convertValue(book, new TypeReference<>() {});
        return new Document(map);
    }

}