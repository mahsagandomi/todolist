package service;

import dto.UserDto;
import exception.ExistException;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;

/**
 * Service class for managing User entities.
 * Handles user creation and password encoding.
 */
@Service
public class UserService {

    private final UserRepository userRepository; // Repository for UserEntity
    private final UserMapper userMapper; // Mapper for User DTOs
    private final PasswordEncoder passwordEncoder; // For encoding user passwords

    /**
     * Constructor for dependency injection.
     */
    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Create a new user.
     * Checks if the username already exists and encodes the password before saving.
     *
     * @param userDto the DTO containing user data
     * @throws ExistException if the username already exists
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void createUser(UserDto userDto) throws ExistException {
        // Check if the username already exists
        if (userRepository.existsByUserName(userDto.getUserName())) {
            throw new ExistException("username already exist.");
        }

        // Encode the user's password
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Save the user entity to the database
        userRepository.save(userMapper.toEntity(userDto));
    }
}
