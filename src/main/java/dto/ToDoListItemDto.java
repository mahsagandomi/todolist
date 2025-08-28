package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for a single To-Do list item.
 * Contains the ID, title, and completion status of the item.
 */
@Schema(name = "ToDoListItem", description = "ToDoListItem Dto")
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
