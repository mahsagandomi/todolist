package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for a User.
 * Contains the user's ID, username, and password.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    // Unique identifier of the user
    private Long id;

    // Username of the user
    private String userName;

    // Password of the user
    private String password;
}
