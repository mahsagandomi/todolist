package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * JPA Entity representing a To-Do list.
 * Contains a list of To-Do items and is linked to a User.
 */
@Entity
@Table(name = "todo_list")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoListEntity {

    // Primary key for the entity, auto-generated
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Title of the to-do list
    private String title;

    // Many-to-one relationship with UserEntity, fetch lazily
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // Foreign key column in the todo_list table
    private UserEntity userEntity;

    // One-to-many relationship with ToDoListItemEntity, cascade all operations, fetch lazily
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "toDoListEntity")
    private List<ToDoListItemEntity> toDoListItems = new ArrayList<>();
}
