package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for a To-Do list item.
 * Contains the ID and title of a to-do item.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoListDto {

    // Unique identifier of the to-do item
    private Long id;

    // Title of the to-do item, serialized as "name" in JSON
    @JsonProperty("name")
    private String title;

}
