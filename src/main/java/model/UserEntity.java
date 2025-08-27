package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * JPA Entity representing a User.
 * Contains username, password, and a list of To-Do lists associated with the user.
 */
@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    // Primary key for the entity, auto-generated
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Username of the user
    private String userName;

    // Password of the user
    private String password;

    // One-to-many relationship with ToDoListEntity, cascade all operations, fetch lazily
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userEntity")
    private List<ToDoListEntity> toDoLists = new ArrayList<>();
}
