package mapper;

import dto.UserDto;
import model.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper interface for converting between User DTOs and Entities.
 * Uses MapStruct to generate the implementation at compile time.
 */
@Mapper(componentModel = "spring") // Tells MapStruct to generate a Spring Bean for dependency injection
public interface UserMapper {

    /**
     * Convert a UserDto to a UserEntity.
     *
     * @param userDto the DTO to convert
     * @return the corresponding entity
     */
    UserEntity toEntity(UserDto userDto);

    /**
     * Convert a UserEntity to a UserDto.
     *
     * @param userEntity the entity to convert
     * @return the corresponding DTO
     */
    UserDto toDto(UserEntity userEntity);

    /**
     * Convert a list of UserDto objects to a list of UserEntity objects.
     *
     * @param userDts the list of DTOs
     * @return the list of corresponding entities
     */
    List<UserEntity> toEntityList(List<UserDto> userDts);

    /**
     * Convert a list of UserEntity objects to a list of UserDto objects.
     *
     * @param userEntities the list of entities
     * @return the list of corresponding DTOs
     */
    List<UserDto> toDtoList(List<UserEntity> userEntities);
}
