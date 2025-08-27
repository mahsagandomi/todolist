package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA Entity representing a single item in a To-Do list.
 * Contains title, completion status, and is linked to a ToDoListEntity.
 */
@Entity
@Table(name = "todo_list_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoListItemEntity {

    // Primary key for the entity, auto-generated
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Title or description of the to-do item
    private String title;

    // Completion status of the to-do item, cannot be null, defaults to false
    @Column(name = "is_done", nullable = false)
    private Boolean isDone = false;

    // Many-to-one relationship with ToDoListEntity, fetch lazily
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todolist_id") // Foreign key column linking to the parent ToDoListEntity
    private ToDoListEntity toDoListEntity;
}
