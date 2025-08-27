package service;

import dto.ToDoListDto;
import dto.ToDoListItemDto;
import exception.ExistException;
import exception.NotFoundException;
import mapper.ToDoListItemMapper;
import mapper.ToDoListMapper;
import model.ToDoListEntity;
import model.ToDoListItemEntity;
import model.UserEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import repository.ToDoListRepository;
import repository.TodoListItemRepository;
import repository.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing ToDoList entities.
 * Handles CRUD operations, user associations, and item management.
 */
@Service
public class ToDoListService {

    private final ToDoListRepository toDoListRepository; // Repository for ToDoListEntity
    private final ToDoListMapper toDoListMapper; // Mapper for ToDoList DTOs
    private final ToDoListItemMapper toDoListItemMapper; // Mapper for ToDoListItem DTOs
    private final TodoListItemRepository toDoListItemRepository; // Repository for ToDoListItemEntity
    private final UserRepository userRepository; // Repository for UserEntity

    /**
     * Constructor for dependency injection.
     */
    public ToDoListService(ToDoListRepository toDoListRepository, ToDoListMapper toDoListMapper,
                           ToDoListItemMapper toDoListItemMapper, TodoListItemRepository toDoListItemRepository,
                           UserRepository userRepository) {
        this.toDoListRepository = toDoListRepository;
        this.toDoListMapper = toDoListMapper;
        this.toDoListItemMapper = toDoListItemMapper;
        this.toDoListItemRepository = toDoListItemRepository;
        this.userRepository = userRepository;
    }

    /**
     * Get all ToDo lists for a specific user by username.
     *
     * @param userName the username of the user
     * @return a list of ToDoListDto
     * @throws NotFoundException if the user does not exist
     */
    public List<ToDoListDto> getAllToDoListByUsername(String userName) throws NotFoundException {
        Optional<UserEntity> user = Optional.ofNullable(
                userRepository.findByUserName(userName)
                        .orElseThrow(() -> new NotFoundException("User not found with username: " + userName))
        );

        return toDoListMapper.toDtoList(toDoListRepository.findByUserEntity_Id(user.get().getId()));
    }

    /**
     * Create a new ToDoList for a specific user.
     *
     * @param toDoListDto the DTO containing list data
     * @param username    the username of the owner
     * @throws ExistException if a list with the same title already exists for the user
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void createToDoList(ToDoListDto toDoListDto, String username) throws ExistException {
        Optional<UserEntity> user = Optional.ofNullable(
                userRepository.findByUserName(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username))
        );

        if (toDoListRepository.existsByTitleAndUserEntity_Id(toDoListDto.getTitle(), user.get().getId())) {
            throw new ExistException("title is exist for this user");
        }

        ToDoListEntity toDoListEntity = toDoListMapper.toEntity(toDoListDto);
        toDoListEntity.setUserEntity(user.get());

        toDoListRepository.save(toDoListEntity);
    }

    /**
     * Delete a ToDoList by its ID.
     *
     * @param toDoListId the ID of the list
     * @throws NotFoundException if the list does not exist
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void deleteToDoList(Long toDoListId) throws NotFoundException {
        toDoListRepository.findById(toDoListId)
                .orElseThrow(() -> new NotFoundException("List not found"));

        toDoListRepository.deleteById(toDoListId);
    }

    /**
     * Add an item to a specific ToDoList.
     *
     * @param listId  the ID of the list
     * @param itemDto the DTO containing item data
     * @throws NotFoundException if the list does not exist
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void addItemToList(Long listId, ToDoListItemDto itemDto) throws NotFoundException {
        ToDoListEntity toDoList = toDoListRepository.findById(listId)
                .orElseThrow(() -> new NotFoundException("List not found"));

        ToDoListItemEntity itemEntity = toDoListItemMapper.toEntity(itemDto);
        itemEntity.setToDoListEntity(toDoList);
        toDoList.getToDoListItems().add(itemEntity);

        toDoListItemRepository.save(itemEntity);
    }

    /**
     * Remove an item from a list by its ID.
     *
     * @param itemId the ID of the item
     * @throws NotFoundException if the item does not exist
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void removeItemFromList(Long itemId) throws NotFoundException {
        if (!toDoListItemRepository.existsById(itemId)) {
            throw new NotFoundException("Item not found");
        }
        toDoListItemRepository.deleteById(itemId);
    }
}
