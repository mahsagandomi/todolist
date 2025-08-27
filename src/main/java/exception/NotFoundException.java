package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception thrown when an entity is not found.
 * Maps to HTTP status 404 NOT FOUND when thrown in a Spring REST controller.
 */
@ResponseStatus(HttpStatus.NOT_FOUND) // Sets the HTTP response status to 404 NOT FOUND
public class NotFoundException extends Exception {

    /**
     * Default no-argument constructor.
     */
    public NotFoundException() {
    }

    /**
     * Constructor with a custom exception message.
     *
     * @param message the exception message
     */
    public NotFoundException(String message) {
        super(message);
    }
}
