package booktracker.validators.messages;

public final class AuthorErrorMessages {

    private AuthorErrorMessages() {
        // Created to prevent instantiation
    }

    public static final String AUTHOR_NAME_REQUIRED =
            "An author name is required";

    public static final String AUTHOR_NAME_TOO_LONG =
            "Author name is too long. It must be less than 100 characters";

    public static final String AUTHOR_SURNAME_REQUIRED =
            "An author surname is required";

    public static final String AUTHOR_SURNAME_TOO_LONG =
            "Author surname is too long. It must be less than 200 characters";

}