package org.example.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(builder = Book.BookBuilder.class)
public class Book {

    private ObjectId id;
    private String title;
    private Author author;
    private Integer publishedIn;
    private List<String> genres;
    private Integer pages;
    private boolean read;
    private Review review;

    @Override
    public String toString() {
        return "id: " + id +
                ", title: '" + title + '\'' +
                ", author: " + author +
                ", publishedIn: " + publishedIn +
                ", genres: " + genres +
                ", pages: " + pages +
                ", read: " + read +
                ", review: " + review;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class BookBuilder {}

}