package booktracker.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(builder = Review.ReviewBuilder.class)
public class Review {

    private int readIn;
    private float rating;
    private String notes;
    private boolean reReadable;

    @Override
    public String toString() {
        return "readIn: " + readIn +
                ", rating: " + rating +
                ", notes: " + notes +
                ", re-readable: " + reReadable;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class ReviewBuilder {}

}