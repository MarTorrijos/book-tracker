package org.example.log;

public final class BookLogMessages {

    public BookLogMessages() {
        // Created to prevent instantiation
    }

    // SAVE
    public static final String SAVE_SUCCESS_LOG =
            "Book titled {} saved successfully";

    public static final String SAVE_FAIL_LOG =
            "Failed to insert book titled {}";

    public static final String SAVE_FAIL_DUPLICATED_TITLE_LOG =
            "Failed to insert book titled {}. Title already exists";


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