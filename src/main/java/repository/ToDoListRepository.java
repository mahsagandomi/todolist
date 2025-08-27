package repository;

import model.ToDoListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for ToDoListEntity.
 * Extends JpaRepository to provide CRUD operations.
 */
@Repository // Marks this interface as a Spring repository bean
public interface ToDoListRepository extends JpaRepository<ToDoListEntity, Long> {

    /**
     * Check if a ToDoList with the given title exists.
     *
     * @param title the title of the to-do list
     * @return true if a list with the title exists, false otherwise
     */
    Boolean existsByTitle(String title);

    /**
     * Delete a ToDoList by its title.
     *
     * @param title the title of the to-do list to delete
     */
    void deleteByTitle(String title);

    /**
     * Find a ToDoList by its title.
     *
     * @param title the title of the to-do list
     * @return an Optional containing the ToDoListEntity if found, empty otherwise
     */
    Optional<ToDoListEntity> findByTitle(String title);

    /**
     * Check if a ToDoList with the given title exists for a specific user.
     *
     * @param title the title of the to-do list
     * @param userId the ID of the user
     * @return true if such a list exists, false otherwise
     */
    boolean existsByTitleAndUserEntity_Id(String title, Long userId);

    /**
     * Find all ToDoListEntity objects belonging to a specific user by user ID.
     *
     * @param userEntityId the ID of the user
     * @return a list of ToDoListEntity objects
     */
    List<ToDoListEntity> findByUserEntity_Id(Long userEntityId);
}
