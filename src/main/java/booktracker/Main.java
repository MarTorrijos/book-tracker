package booktracker;

import booktracker.util.MenuCommand;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        MenuCommand.menu("diu");

//        // Connect to MongoDB
//        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
//        MongoDatabase mongoDatabase = mongoClient.getDatabase("library");
//
//        // Get collection
//        MongoCollection<Document> collection = mongoDatabase.getCollection("books");
//
//        // Mapper
//        ObjectMapper mapper = new ObjectMapper();
//
//        // ✅ Validator setup
//        BookValidator validator = BookValidatorFactory.create();
//
//        // ✅ Service wiring
//        BookService bookService = new BookService(collection, mapper, validator);
//
//        BookController bookController = new BookController(bookService);

        System.out.println("Introduce un título");
        String name = scanner.nextLine();

    }

}