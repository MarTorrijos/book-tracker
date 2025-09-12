package booktracker.dao.stats;

import booktracker.entities.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.List;
import java.util.Map;

public class BookStatsDaoImpl implements BookStatsDao {

    private final MongoCollection<Document> collection;
    private final ObjectMapper mapper;

    public BookStatsDaoImpl(MongoCollection<Document> collection, ObjectMapper mapper) {
        this.collection = collection;
        this.mapper = mapper;
    }

    @Override
    public int countBooksReadThisYear() {

    }

    @Override
    public int countBooksReadInGivenYear(int year) {

    }

    @Override
    public int countTotalBooksRead() {

    }

    // Esto me está dando error porque no existe en BookStatsDao
    // Existe en BookDao, pero creo que lo voy a necesitar aquí también
    // Debería traerlo?
    @Override
    public List<Book> findAllBooks() {
        return collection.find()
                .into(new java.util.ArrayList<>())
                .stream()
                .map(doc -> mapper.convertValue(doc, Book.class))
                .toList();
    }

    private Document toDocument(Book book) {
        Map<String, Object> map = mapper.convertValue(book, new TypeReference<>() {});
        return new Document(map);
    }

}