package booktracker.log;

public final class BookQueryLogMessages {

    public BookQueryLogMessages() {
        // Created to prevent instantiation
    }

    // FIND
    public static final String FIND_BY_ID_SUCCESS_LOG =
            "Book with id {} found";

    public static final String FIND_BY_TITLE_SUCCESS_LOG =
            "Book with title {} found";

    public static final String FIND_BY_ID_FAIL_LOG =
            "Book with id {} couldn't be found";

    public static final String FIND_BY_TITLE_FAIL_LOG =
            "Book with title {} couldn't be found";

    public static final String FIND_BY_AUTHOR_SUCCESS =
            "Book/s by author {} found";

    public static final String FIND_BY_AUTHOR_FAIL =
            "Book/s by author {} couldn't be found";

}