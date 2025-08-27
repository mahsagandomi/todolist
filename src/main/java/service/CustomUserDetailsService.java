package service;

import model.UserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

/**
 * Custom implementation of Spring Security's UserDetailsService.
 * Loads user details from the database for authentication purposes.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Repository to access UserEntity data
    private final UserRepository userRepository;

    /**
     * Constructor to inject UserRepository.
     *
     * @param userRepository the UserRepository to use
     */
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads a user by their username.
     * Used by Spring Security during authentication.
     *
     * @param username the username of the user
     * @return UserDetails containing username, password, and authorities
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find the user by username or throw an exception if not found
        Optional<UserEntity> user = Optional.ofNullable(
                userRepository.findByUserName(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username))
        );

        // Return a Spring Security User object with username, password, and empty authorities
        return new User(
                user.get().getUserName(),
                user.get().getPassword(),
                Collections.emptyList() // No roles/authorities
        );
    }
}
