package org.example.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Setter
@Builder
public class Book {

    private ObjectId id;
    private String title;
    private Author author;
    private int publishedIn;
    private List<String> genres;
    private int pages;
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

}