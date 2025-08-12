package org.example.dao.author;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import org.example.entities.Author;

import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class AuthorDaoImpl implements AuthorDao {

    private final MongoCollection<Document> collection;
    private final ObjectMapper mapper;

    public AuthorDaoImpl(MongoCollection<Document> collection, ObjectMapper mapper) {
        this.collection = collection;
        this.mapper = mapper;
    }

    @Override
    public Author findByName(String name) {
        Document doc = collection.find(eq("name", name)).first();
        return doc != null ? mapper.convertValue(doc, Author.class) : null;
    }

    @Override
    public Author insert(Author author) {
        Document doc = new Document("name", author.getName());
        collection.insertOne(doc);
        author.setId(doc.getObjectId("_id"));
        return author;
    }

}