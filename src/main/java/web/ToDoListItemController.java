package web;

import dto.ToDoListItemDto;
import exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ToDoListItemService;

import java.util.List;

/**
 * REST controller for managing ToDoList items.
 * Handles CRUD operations and toggling item status.
 */
@RestController
@RequestMapping("/todo-items")
public class ToDoListItemController {

    private final ToDoListItemService toDoListItemService; // Service to handle business logic

    /**
     * Constructor for dependency injection.
     *
     * @param toDoListItemService the ToDoListItemService to use
     */
    @Autowired
    public ToDoListItemController(ToDoListItemService toDoListItemService) {
        this.toDoListItemService = toDoListItemService;
    }



    @Operation(summary = "Get all todolist item", description = "Get all todolist with todolist id")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Successfully retrieved all items for the ToDo list",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ToDoListItemDto.class)))),
            @ApiResponse(responseCode = "404", description = "ToDo list not found")})
    @GetMapping("/todolist-item/{toDoListId}")
    public ResponseEntity<List<ToDoListItemDto>> getAllToDoListItem(@PathVariable Long toDoListId) {
        return ResponseEntity.ok(toDoListItemService.getAllToDoListItem(toDoListId));
    }



    @Operation(summary = "Create a new ToDoList item", description = "Creates a new item in the specified ToDo list")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "Item created successfully"),
            @ApiResponse(responseCode = "404", description = "Parent ToDo list not found")})
    @PostMapping("/{toDoListId}")
    public ResponseEntity<Void> createItem(@RequestBody ToDoListItemDto toDoListItemDto, @PathVariable Long toDoListId) throws NotFoundException {
        toDoListItemService.createItem(toDoListItemDto, toDoListId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    @Operation(summary = "Delete an item", description = "Deletes the item with the specified ID")
    @ApiResponses({@ApiResponse(responseCode = "204", description = "Item deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Item not found")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        toDoListItemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }



    @Operation(summary = "Toggle item status", description = "Toggles the 'isDone' status of a ToDoList item")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Status updated successfully"),
            @ApiResponse(responseCode = "404", description = "Item not found")})
    @PatchMapping("/{id}/toggle-done")
    public ResponseEntity<Void> updateIsDone(@PathVariable Long id) throws NotFoundException {
        toDoListItemService.updateIsDone(id);
        return ResponseEntity.ok().build();
    }
}
