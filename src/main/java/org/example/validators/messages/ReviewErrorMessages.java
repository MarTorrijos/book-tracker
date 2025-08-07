package org.example.validators.messages;

public final class ReviewErrorMessages {

    public ReviewErrorMessages() {
        // Created to prevent instantiation
    }

    public static final String NOTES_TOO_LONG =
            "Notes are too long. They can't exceed 1000 characters";

    public static final String INCORRECT_RATING =
            "Rating must be a number between 1 and 5, with up to one decimal";

    public static final String INCORRECT_YEAR_READ_IN =
            "You couldn't have read the book before you were born or in the future";

}