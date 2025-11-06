package booktracker.exceptions;

public class DuplicatedBookException extends RuntimeException {
    public DuplicatedBookException(String message) {
        super(message);
    }
}
