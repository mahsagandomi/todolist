package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for a single To-Do list item.
 * Contains the ID, title, and completion status of the item.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoListItemDto {

    // Unique identifier of the to-do item
    private Long id;

    // Title or description of the to-do item
    private String title;

    // Completion status of the item, defaults to false
    private Boolean isDone = false;

}
