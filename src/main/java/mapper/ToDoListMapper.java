package mapper;

import dto.ToDoListDto;
import model.ToDoListEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper interface for converting between ToDoList DTOs and Entities.
 * Uses MapStruct to generate the implementation at compile time.
 */
@Mapper(componentModel = "spring") // Tells MapStruct to generate a Spring Bean for dependency injection
public interface ToDoListMapper {

    /**
     * Convert a ToDoListDto to a ToDoListEntity.
     *
     * @param ToDoListDto the DTO to convert
     * @return the corresponding entity
     */
    ToDoListEntity toEntity(ToDoListDto ToDoListDto);

    /**
     * Convert a ToDoListEntity to a ToDoListDto.
     *
     * @param ToDoListEntity the entity to convert
     * @return the corresponding DTO
     */
    ToDoListDto toDto(ToDoListEntity ToDoListEntity);

    /**
     * Convert a list of ToDoListDto objects to a list of ToDoListEntity objects.
     *
     * @param ToDoListDts the list of DTOs
     * @return the list of corresponding entities
     */
    List<ToDoListEntity> toEntityList(List<ToDoListDto> ToDoListDts);

    /**
     * Convert a list of ToDoListEntity objects to a list of ToDoListDto objects.
     *
     * @param ToDoListEntities the list of entities
     * @return the list of corresponding DTOs
     */
    List<ToDoListDto> toDtoList(List<ToDoListEntity> ToDoListEntities);
}
