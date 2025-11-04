package booktracker.service.author.logs;

public class AuthorLogMessages {

    public static final String SAVE_AUTHOR_SUCCESS_LOG =
            "Author {} saved successfully";

    public static final String SAVE_AUTHOR_FAIL_LOG =
            "Failed to saveAuthor author named {}. Author already exists";

    public static final String FIND_AUTHOR_BY_SURNAME_SUCCESS_LOG =
            "Author/s by the surname {} found";

    public static final String FIND_AUTHOR_BY_SURNAME_FAIL_LOG =
            "No authors found by the surname {}";

    public static final String FIND_AUTHOR_BY_FULL_NAME_SUCCESS_LOG =
            "Author by the name {} {} found";

    public static final String FIND_AUTHOR_BY_FULL_NAME_FAIL_LOG =
            "Author by the name {} {} couldn't be found";

}
