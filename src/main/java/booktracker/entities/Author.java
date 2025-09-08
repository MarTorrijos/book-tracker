package booktracker.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(builder = Author.AuthorBuilder.class)
public class Author {

    private ObjectId id;
    private String name;

    @Override
    public String toString() {
        return name;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class AuthorBuilder {}

}