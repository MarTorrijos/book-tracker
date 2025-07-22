package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Review {

    private int readIn;
    private int rating;
    private String notes;
    private boolean reReadable;

}