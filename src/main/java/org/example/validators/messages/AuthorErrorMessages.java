package org.example.validators.messages;

public final class AuthorErrorMessages {

    private AuthorErrorMessages() {
        // Created to prevent instantiation
    }

    public static final String AUTHOR_NAME_REQUIRED =
        "An author name is required";

    public static final String AUTHOR_NAME_TOO_LONG =
            "Author name is too long. It must be less than 300 characters";

}