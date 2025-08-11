package org.example.service.book;

public final class BookLogMessages {

    public BookLogMessages() {
        // Created to prevent instantiation
    }

    public static final String SAVE_SUCCESS_LOG =
            "Book titled {} saved successfully";

    public static final String SAVE_FAIL_LOG =
            "Failed to save book titled {}";

    public static final String SAVE_FAIL_DUPLICATED_TITLE_LOG =
            "Failed to save book titled {}. Title already exists";

    public static final String FIND_SUCCESS_LOG =
            "Book with id {} found";

    public static final String FIND_FAIL_LOG =
            "Book with id {} couldn't be found";

    public static final String UPDATE_SUCCESS_LOG =
            "Book with id {} updated successfully";

    public static final String UPDATE_FAIL_LOG =
            "No book found with id {}. Couldn't update it";

    public static final String DELETE_SUCCESS_LOG =
            "Book with id {} deleted";

    public static final String DELETE_FAIL_LOG =
            "No book found with id {}. Couldn't delete it";

}