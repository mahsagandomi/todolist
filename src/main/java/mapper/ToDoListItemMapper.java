package mapper;

import dto.ToDoListDto;
import dto.ToDoListItemDto;
import model.ToDoListEntity;
import model.ToDoListItemEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper interface for converting between ToDoListItem DTOs and Entities.
 * Uses MapStruct to generate the implementation at compile time.
 */
@Mapper(componentModel = "spring") // Tells MapStruct to generate a Spring Bean for dependency injection
public interface ToDoListItemMapper {

    /**
     * Convert a ToDoListItemDto to a ToDoListItemEntity.
     *
     * @param ToDoListItemDto the DTO to convert
     * @return the corresponding entity
     */
    ToDoListItemEntity toEntity(ToDoListItemDto ToDoListItemDto);

    /**
     * Convert a ToDoListEntity to a ToDoListItemDto.
     *
     * @param ToDoListItemEntity the entity to convert
     * @return the corresponding DTO
     */
    ToDoListItemDto toDto(ToDoListEntity ToDoListItemEntity);

    /**
     * Convert a list of ToDoListItemDto objects to a list of ToDoListItemEntity objects.
     *
     * @param ToDoListItemDts the list of DTOs
     * @return the list of corresponding entities
     */
    List<ToDoListItemEntity> toEntityList(List<ToDoListItemDto> ToDoListItemDts);

    /**
     * Convert a list of ToDoListItemEntity objects to a list of ToDoListItemDto objects.
     *
     * @param ToDoListItemEntities the list of entities
     * @return the list of corresponding DTOs
     */
    List<ToDoListItemDto> toDtoList(List<ToDoListItemEntity> ToDoListItemEntities);
}
