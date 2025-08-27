package service;

import dto.RequestDto;
import dto.UserDto;
import exception.ExistException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;

/**
 * Service class responsible for registering new users.
 * Checks for existing usernames and delegates user creation to UserService.
 */
@Service
public class RegisterService {

    // Service for managing users
    private final UserService userService;

    // Repository to access UserEntity data
    private final UserRepository userRepository;

    /**
     * Constructor to inject dependencies.
     *
     * @param userService    the UserService to use
     * @param userRepository the UserRepository to use
     */
    public RegisterService(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    /**
     * Registers a new user based on the provided request data.
     * Throws an ExistException if the username already exists.
     *
     * @param requestDto the DTO containing user registration data
     * @throws ExistException if the username is already taken
     */
    @Transactional // Ensures that the registration process is transactional
    public void register(RequestDto requestDto) throws ExistException {

        // Check if the username already exists in the database
        if (userRepository.existsByUserName(requestDto.getUserName())) {
            throw new ExistException("username is already exist");
        }

        // Create a new UserDto and set its fields
        UserDto userDto = new UserDto();
        userDto.setUserName(requestDto.getUserName());
        userDto.setPassword(requestDto.getPassword());

        // Delegate the creation of the user to the UserService
        userService.createUser(userDto);
    }
}
