package booktracker.config;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoConnectionManager {

    private MongoClient client;

    public MongoClient getClient() {
        if (client == null) {
            try {
                client = MongoClients.create("mongodb://localhost:27017");
                // Trigger a connection check
                client.getDatabase("library").listCollectionNames().first();
                System.out.println("Connected to MongoDB successfully.");
            } catch (MongoException e) {
                System.err.println("Error connecting to MongoDB: " + e.getMessage());
                throw e;
            }
        }
        return client;
    }

    public void close() {
        if (client != null) {
            client.close();
        }
    }

}