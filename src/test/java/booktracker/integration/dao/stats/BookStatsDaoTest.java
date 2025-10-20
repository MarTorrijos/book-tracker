package booktracker.integration.dao.stats;

import booktracker.dao.stats.BookStatsDaoImpl;
import booktracker.entities.Book;
import booktracker.testdata.BookListDataProvider;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Year;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("integration")
@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookStatsDaoTest {

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0");

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    private BookStatsDaoImpl dao;

    @BeforeAll
    void setUpAll() {
        mongoClient = MongoClients.create(mongoDBContainer.getConnectionString());
    }

    @AfterAll
    void tearDownAll() {
        mongoClient.close();
    }

    @BeforeEach
    void setUp() {
        database = mongoClient.getDatabase("booktracker_test_db");
        collection = database.getCollection("books");
        collection.drop();

        List<Book> books = BookListDataProvider.statsBookList();
        List<Document> docs = books.stream()
                .map(this::convertBookToDocument)
                .toList();

        collection.insertMany(docs);
        dao = new BookStatsDaoImpl(collection);
    }

    @Test
    @DisplayName("Verifies the count of books read in the current year matches the expected value")
    void testCountBooksReadThisYear() {
        int expected = (int) BookListDataProvider.statsBookList().stream()
                .filter(Book::isRead)
                .filter(b -> b.getReview().getReadIn() == Year.now().getValue())
                .count();

        int actual = dao.countBooksReadThisYear();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Verifies the count of books read in the given year matches the expected value")
    void testCountBooksReadInGivenYear() {
        int expected = (int) BookListDataProvider.statsBookList().stream()
                .filter(Book::isRead)
                .filter(b -> b.getReview().getReadIn() == 2025)
                .count();

        int actual = dao.countBooksReadInGivenYear(2025);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Verifies that the total number of books read matches the expected value")
    void testCountTotalBooksRead() {
        int expected = (int) BookListDataProvider.statsBookList().stream()
                .filter(Book::isRead)
                .count();

        int actual = dao.countTotalBooksRead();

        assertEquals(expected, actual);
    }

    // Document conversion helper
    private Document convertBookToDocument(Book book) {
        Document doc = new Document();
        doc.append("_id", book.getId());
        doc.append("title", book.getTitle());
        doc.append("read", book.isRead());

        Document authorDoc = new Document();
        authorDoc.append("name", book.getAuthor().getName());
        doc.append("author", authorDoc);

        if (book.getReview() != null) {
            Document reviewDoc = new Document();
            reviewDoc.append("readIn", book.getReview().getReadIn());
            reviewDoc.append("rating", book.getReview().getRating());
            reviewDoc.append("comment", book.getReview().getNotes());
            reviewDoc.append("re-readable", book.getReview().isReReadable());
            doc.append("review", reviewDoc);
        }

        return doc;
    }

}