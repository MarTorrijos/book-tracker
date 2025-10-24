package booktracker;

import booktracker.config.MongoConnectionManager;
import booktracker.exceptions.DataAccessException;
import booktracker.util.MenuCommand;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Scanner;

public class BookTracker {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        MongoConnectionManager connectionManager = new MongoConnectionManager();

        // esto está aquí solo para recordar que quiero usar MenuCommand
        MenuCommand.menu("diu");

        try {
            MongoClient client = connectionManager.getClient();
            MongoCollection<Document> collection = client.getDatabase("library").getCollection("books");

            // Menú de inicio, supongo

        } catch (MongoException e) {
            System.err.println("Database connection problem: " + e.getMessage());
            System.exit(1);  // exit or handle retry here
        } catch (DataAccessException e) {
            System.err.println("Error accessing data: " + e.getMessage());
            // Could prompt user or retry
        } finally {
            connectionManager.close();
        }
    }

}