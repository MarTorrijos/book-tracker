package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class Author {

    private ObjectId id;
    private String name;

    public Author(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}