package web;

import dto.ToDoListDto;
import dto.ToDoListItemDto;
import exception.ExistException;
import exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ToDoListService;

import java.security.Principal;
import java.util.List;

/**
 * REST controller for managing ToDoLists and their items.
 * Handles CRUD operations and item additions.
 */
@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/todolists")
public class ToDoListController {

    private final ToDoListService toDoListService; // Service to handle business logic

    /**
     * Constructor for dependency injection.
     *
     * @param toDoListService the ToDoListService to use
     */
    public ToDoListController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }


    @Operation(summary = "Get all ToDo lists", description = "Retrieve all ToDo lists of the currently authenticated user")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved lists",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ToDoListDto.class))))
    @GetMapping
    public ResponseEntity<List<ToDoListDto>> getAllToDoList(Principal principal) throws NotFoundException {
        String username = principal.getName(); // Get username from token/session
        return ResponseEntity.ok(toDoListService.getAllToDoListByUsername(username));
    }


    @Operation(summary = "Create a new ToDo list", description = "Create a new ToDo list for the logged-in user")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "ToDo list created successfully"),
            @ApiResponse(responseCode = "409", description = "List with the same title already exists")})
    @PostMapping
    public ResponseEntity<String> createToDoList(@RequestBody ToDoListDto toDoListDto, Principal principal) throws ExistException {
        String userName = principal.getName();
        toDoListService.createToDoList(toDoListDto, userName);
        return ResponseEntity.ok("to do list added.");
    }


    @Operation(summary = "Delete todolist", description = "Delete todolist with todolist id")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "ToDo list deleted successfully"),
            @ApiResponse(responseCode = "404", description = "List not found")})
    @DeleteMapping("/{toDoListId}")
    public ResponseEntity<String> deleteToDoList(@PathVariable Long toDoListId) throws NotFoundException {
        toDoListService.deleteToDoList(toDoListId);
        return ResponseEntity.ok("deleted.");
    }


    @Operation(summary = "Add item to a ToDo list", description = "Add an item to a specific ToDo list by ID")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Item added successfully"),
            @ApiResponse(responseCode = "404", description = "ToDo list not found")})
    @PostMapping("/{id}")
    public ResponseEntity<String> addItemToList(@PathVariable Long id, @RequestBody ToDoListItemDto toDoListItemDto) throws NotFoundException {
        toDoListService.addItemToList(id, toDoListItemDto);
        return ResponseEntity.ok("added.");
    }
}
