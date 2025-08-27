package repository;

import model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for UserEntity.
 * Extends JpaRepository to provide CRUD operations.
 */
@Repository // Marks this interface as a Spring repository bean
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Check if a user with the given username exists.
     *
     * @param userName the username to check
     * @return true if a user with the username exists, false otherwise
     */
    Boolean existsByUserName(String userName);

    /**
     * Find a user by their username.
     *
     * @param userName the username of the user
     * @return an Optional containing the UserEntity if found, empty otherwise
     */
    Optional<UserEntity> findByUserName(String userName);
}
