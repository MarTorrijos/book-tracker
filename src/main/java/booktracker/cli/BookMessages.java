package booktracker.cli;

public final class BookMessages {

    public BookMessages() {
        // Created to prevent instantiation
    }

    public static final String SAVE_SUCCESS_MSG =
            "Book saved";

    public static final String SAVE_FAIL_MSG =
            "Failed to saveAuthor book";

    public static final String SAVE_FAIL_DUPLICATED_TITLE_MSG =
            "Failed to saveAuthor book. Title already exists";

    public static final String FIND_FAIL_MSG =
            "Book not found";

    public static final String UPDATE_SUCCESS_MSG =
            "Book updated";

    public static final String UPDATE_FAIL_MSG =
            "Book not found. Couldn't update it";

    public static final String DELETE_SUCCESS_MSG =
            "Book deleted";

    public static final String DELETE_FAIL_MSG =
            "Book not found. Couldn't be deleted";

}