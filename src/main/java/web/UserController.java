package web;

import dto.UserDto;
import exception.ExistException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing users.
 * Handles user creation requests.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService; // Service to handle business logic

    /**
     * Constructor for dependency injection.
     *
     * @param userService the UserService to use
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @Operation(summary = "Create a new user", description = "Registers a new user in the system")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "409", description = "Username already exists")})
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) throws ExistException {
        // Delegate user creation to the service
        userService.createUser(userDto);

        // Return success response with HTTP status 201 CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body("user added");
    }
}
