package booktracker.exceptions;

public class DuplicatedAuthorException extends RuntimeException {
    public DuplicatedAuthorException(String message) {
        super(message);
    }
}
