package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for handling login or registration requests.
 * Contains user credentials: username and password.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {

    // The username of the user
    private String userName;

    // The password of the user
    private String password;

}
