package booktracker.validators.messages;

public final class BookErrorMessages {

    public BookErrorMessages() {
        // Created to prevent instantiation
    }

    public static final String GENRE_TOO_LONG =
            "Genre is too long. It must be less than 50 characters";

    public static final String PAGES_NOT_POSITIVE =
            "Page count must be a positive number";

    public static final String PAGE_COUNT_TOO_HIGH =
            "Page count is too high. It can't be greater than 2000";

    public static final String PUBLISHED_IN_NOT_VALID =
            "Published year must be valid (between 0 and %d)";

    public static final String TITLE_REQUIRED =
            "Book title can't be empty";

    public static final String TITLE_TOO_LONG =
            "Book title can't exceed 200 characters";

}