package web;

import dto.RequestDto;
import exception.ExistException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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



    @Operation(summary = "Register to TodoList", description = "Register a new user to the application using RequestDto")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Registration successful",
            content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "409", description = "Username already exists")})
    @PostMapping
    public ResponseEntity<String> register(@RequestBody RequestDto requestDTo) throws ExistException {
        // Delegate registration to the service
        registerService.register(requestDTo);

        // Return success response
        return ResponseEntity.ok("added.");
    }
}
