package service;

import dto.ToDoListItemDto;
import exception.NotFoundException;
import mapper.ToDoListItemMapper;
import model.ToDoListEntity;
import model.ToDoListItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import repository.ToDoListRepository;
import repository.TodoListItemRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing ToDoListItem entities.
 * Handles CRUD operations and mapping between DTOs and entities.
 */
@Service
public class ToDoListItemService {

    // Repository to access ToDoListItemEntity data
    private final TodoListItemRepository todoListItemRepository;

    // Mapper to convert between DTOs and entities
    private final ToDoListItemMapper toDoListItemMapper;

    // Repository to access ToDoListEntity data
    private final ToDoListRepository toDoListRepository;

    /**
     * Constructor for dependency injection.
     *
     * @param todoListItemRepository repository for ToDoListItemEntity
     * @param toDoListItemMapper     mapper for ToDoListItem
     * @param toDoListRepository     repository for ToDoListEntity
     */
    @Autowired
    public ToDoListItemService(TodoListItemRepository todoListItemRepository, ToDoListItemMapper toDoListItemMapper, ToDoListRepository toDoListRepository) {
        this.todoListItemRepository = todoListItemRepository;
        this.toDoListItemMapper = toDoListItemMapper;
        this.toDoListRepository = toDoListRepository;
    }

    /**
     * Get all items for a specific ToDoList by its ID.
     *
     * @param toDoListId the ID of the ToDoList
     * @return a list of ToDoListItemDto
     */
    @Transactional(readOnly = true,propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED) // Read-only transaction
    public List<ToDoListItemDto> getAllToDoListItem(Long toDoListId) {
        return toDoListItemMapper.toDtoList(todoListItemRepository.findByToDoListEntity_Id(toDoListId));
    }

    /**
     * Create a new item for a specific ToDoList.
     *
     * @param toDoListItemDto DTO containing item data
     * @param todoListId      ID of the parent ToDoList
     * @throws NotFoundException if the parent ToDoList does not exist
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void createItem(ToDoListItemDto toDoListItemDto, Long todoListId) throws NotFoundException {
        // Find the parent ToDoListEntity or throw exception if not found
        Optional<ToDoListEntity> toDoListEntity = Optional.ofNullable(
                toDoListRepository.findById(todoListId)
                        .orElseThrow(() -> new NotFoundException("Todolist not found"))
        );

        // Map DTO to entity
        ToDoListItemEntity itemEntity = toDoListItemMapper.toEntity(toDoListItemDto);

        // Set relationship to parent ToDoListEntity
        itemEntity.setToDoListEntity(toDoListEntity.get());

        // Save the item entity
        todoListItemRepository.save(itemEntity);
    }

    /**
     * Delete an item by its ID.
     *
     * @param itemId the ID of the item to delete
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void deleteItem(Long itemId) {
        todoListItemRepository.deleteById(itemId);
    }

    /**
     * Toggle the "isDone" status of a ToDoListItem.
     *
     * @param itemId the ID of the item
     * @throws NotFoundException if the item does not exist
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void updateIsDone(Long itemId) throws NotFoundException {
        // Find the item or throw exception
        ToDoListItemEntity toDoListItemEntity = todoListItemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("item not found"));

        // Toggle the isDone status
        Boolean isDone = toDoListItemEntity.getIsDone();
        toDoListItemEntity.setIsDone(!isDone);

        // Save the updated entity
        todoListItemRepository.save(toDoListItemEntity);
    }
}
