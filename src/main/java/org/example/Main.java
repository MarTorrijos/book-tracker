package org.example;

import org.example.entities.Author;
import org.example.entities.Book;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Book book = Book.builder()
                .title("Dune")
                .author(new Author("Frank Herbert"))
                .pages(412)
                .read(true)
                .genres(List.of("Sci-Fi", "Adventure"))
                .build();

        System.out.println(book);
    }

}