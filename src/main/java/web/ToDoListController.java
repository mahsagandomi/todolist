package web;

import dto.ToDoListDto;
import dto.ToDoListItemDto;
import exception.ExistException;
import exception.NotFoundException;
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

    /**
     * Get all ToDo lists for the logged-in user.
     *
     * @param principal the currently authenticated user
     * @return a list of ToDoListDto
     * @throws NotFoundException if the user does not exist
     */
    @GetMapping
    public ResponseEntity<List<ToDoListDto>> getAllToDoList(Principal principal) throws NotFoundException {
        String username = principal.getName(); // Get username from token/session
        return ResponseEntity.ok(toDoListService.getAllToDoListByUsername(username));
    }

    /**
     * Create a new ToDo list for the logged-in user.
     *
     * @param toDoListDto DTO containing ToDo list data
     * @param principal   the currently authenticated user
     * @return ResponseEntity with success message
     * @throws ExistException if a list with the same title already exists for the user
     */
    @PostMapping
    public ResponseEntity<String> createToDoList(@RequestBody ToDoListDto toDoListDto, Principal principal) throws ExistException {
        String userName = principal.getName();
        toDoListService.createToDoList(toDoListDto, userName);
        return ResponseEntity.ok("to do list added.");
    }

    /**
     * Delete a ToDo list by its ID.
     *
     * @param toDoListId the ID of the list to delete
     * @return ResponseEntity with success message
     * @throws NotFoundException if the list does not exist
     */
    @DeleteMapping("/{toDoListId}")
    public ResponseEntity<String> deleteToDoList(@PathVariable Long toDoListId) throws NotFoundException {
        toDoListService.deleteToDoList(toDoListId);
        return ResponseEntity.ok("deleted.");
    }

    /**
     * Add an item to a specific ToDo list.
     *
     * @param id              the ID of the ToDo list
     * @param toDoListItemDto DTO containing item data
     * @return ResponseEntity with success message
     * @throws NotFoundException if the list does not exist
     */
    @PostMapping("/{id}")
    public ResponseEntity<String> addItemToList(@PathVariable Long id, @RequestBody ToDoListItemDto toDoListItemDto) throws NotFoundException {
        toDoListService.addItemToList(id, toDoListItemDto);
        return ResponseEntity.ok("added.");
    }
}
