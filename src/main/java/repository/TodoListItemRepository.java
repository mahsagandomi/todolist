package repository;

import model.ToDoListItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for ToDoListItemEntity.
 * Extends JpaRepository to provide CRUD operations.
 */
@Repository // Marks this interface as a Spring repository bean
public interface TodoListItemRepository extends JpaRepository<ToDoListItemEntity, Long> {

    /**
     * Find all ToDoListItemEntity objects belonging to a specific ToDoListEntity by its ID.
     *
     * @param toDoListEntityId the ID of the parent ToDoListEntity
     * @return a list of ToDoListItemEntity objects
     */
    List<ToDoListItemEntity> findByToDoListEntity_Id(Long toDoListEntityId);
}
