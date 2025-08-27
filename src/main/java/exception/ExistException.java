package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception thrown when an entity already exists.
 * Maps to HTTP status 409 CONFLICT when thrown in a Spring REST controller.
 */
@ResponseStatus(HttpStatus.CONFLICT) // Sets the HTTP response status to 409 CONFLICT
public class ExistException extends Exception {

    /**
     * Default no-argument constructor.
     */
    public ExistException() {
    }

    /**
     * Constructor with a custom exception message.
     *
     * @param message the exception message
     */
    public ExistException(String message) {
        super(message);
    }
}
