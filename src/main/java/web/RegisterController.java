package web;

import dto.RequestDto;
import exception.ExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.RegisterService;

/**
 * REST controller for user registration.
 * Handles incoming registration requests and delegates to RegisterService.
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    private final RegisterService registerService; // Service to handle registration logic

    /**
     * Constructor for dependency injection.
     *
     * @param registerService the RegisterService to use
     */
    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    /**
     * Handles POST requests to register a new user.
     *
     * @param requestDTo DTO containing user registration data
     * @return ResponseEntity with success message
     * @throws ExistException if the username already exists
     */
    @PostMapping
    public ResponseEntity<String> register(@RequestBody RequestDto requestDTo) throws ExistException {
        // Delegate registration to the service
        registerService.register(requestDTo);

        // Return success response
        return ResponseEntity.ok("added.");
    }
}
