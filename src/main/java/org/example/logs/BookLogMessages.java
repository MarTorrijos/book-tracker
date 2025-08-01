package org.example.logs;

public final class BookLogMessages {

    private BookLogMessages() {};

    public static final String SAVE_SUCCESS =
            "Book titled {} saved successfully";

    public static final String SAVE_FAIL =
            "Failed to save book due to internal error";

    public static final String DUPLICATED_TITLE =
            "Couldn't save duplicated book title: {}";

    public static final String UPDATE_SUCCESS =
            "Book updated successfully: {}";

}