package web;

import dto.ToDoListItemDto;
import exception.NotFoundException;
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

    /**
     * Get all items for a specific ToDoList by its ID.
     *
     * @param toDoListId the ID of the ToDoList
     * @return a list of ToDoListItemDto
     */
    @GetMapping("/todolist-item/{toDoListId}")
    public ResponseEntity<List<ToDoListItemDto>> getAllToDoListItem(@PathVariable Long toDoListId) {
        return ResponseEntity.ok(toDoListItemService.getAllToDoListItem(toDoListId));
    }

    /**
     * Create a new item for a specific ToDoList.
     *
     * @param toDoListItemDto DTO containing item data
     * @param toDoListId      ID of the parent ToDoList
     * @return ResponseEntity with HTTP status CREATED
     * @throws NotFoundException if the parent ToDoList does not exist
     */
    @PostMapping("/{toDoListId}")
    public ResponseEntity<Void> createItem(@RequestBody ToDoListItemDto toDoListItemDto, @PathVariable Long toDoListId) throws NotFoundException {
        toDoListItemService.createItem(toDoListItemDto, toDoListId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Delete an item by its ID.
     *
     * @param id the ID of the item
     * @return ResponseEntity with HTTP status NO_CONTENT
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        toDoListItemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Toggle the "isDone" status of a ToDoListItem.
     *
     * @param id the ID of the item
     * @return ResponseEntity with HTTP status OK
     * @throws NotFoundException if the item does not exist
     */
    @PatchMapping("/{id}/toggle-done")
    public ResponseEntity<Void> updateIsDone(@PathVariable Long id) throws NotFoundException {
        toDoListItemService.updateIsDone(id);
        return ResponseEntity.ok().build();
    }
}
