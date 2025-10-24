package booktracker.log;

public final class BookCrudLogMessages {

    // SAVE
    public static final String SAVE_SUCCESS_LOG =
            "Book titled {} saved successfully";

    public static final String SAVE_FAIL_LOG =
            "Failed to insert book titled {}";

    public static final String SAVE_FAIL_DUPLICATED_TITLE_LOG =
            "Failed to insert book titled {}. Title already exists";


    // UPDATE
    public static final String UPDATE_SUCCESS_LOG =
            "Book with id {} updated successfully";

    public static final String UPDATE_FAIL_LOG =
            "No book found with id {}. Couldn't update it";


    // DELETE
    public static final String DELETE_SUCCESS_LOG =
            "Book with id {} deleted";

    public static final String DELETE_FAIL_LOG =
            "No book found with id {}. Couldn't delete it";

}