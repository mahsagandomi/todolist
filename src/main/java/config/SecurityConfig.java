package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for the Spring application.
 * This class sets up password encoding, authentication management,
 * and HTTP security rules including login, logout, and URL access permissions.
 */
@Configuration
public class SecurityConfig {

    /**
     * Bean definition for password encoding using BCrypt.
     * This is used to securely hash user passwords before storing in the database.
     *
     * @return a PasswordEncoder instance using BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Return a BCryptPasswordEncoder to hash passwords securely
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean definition for AuthenticationManager.
     * This allows Spring Security to handle authentication for login requests.
     *
     * @param authenticationConfiguration the configuration that provides the AuthenticationManager
     * @return the AuthenticationManager instance
     * @throws Exception if there is a problem creating the AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // Retrieve and return the AuthenticationManager from the configuration
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Bean definition for SecurityFilterChain.
     * This configures HTTP security, including CSRF, URL authorization, login, and logout settings.
     *
     * @param http the HttpSecurity object to configure
     * @return the built SecurityFilterChain
     * @throws Exception if there is a problem configuring HTTP security
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection
                .csrf(csrf -> csrf.disable())
                // Configure URL authorization rules
                .authorizeHttpRequests(auth -> auth
                        // Permit access to log in, register pages and static resources without authentication
                        .requestMatchers(
                                "/login.html",
                                "/register.html",
                                "/style.css",
                                "/users",
                                "/register"
                        ).permitAll()
                        // All other requests require authentication
                        .anyRequest().authenticated()
                )
                // Configure form-based login
                .formLogin(form -> form
                        // Set custom login page
                        .loginPage("/login.html")
                        // Set URL for processing login POST requests
                        .loginProcessingUrl("/login")
                        // Set default URL after successful login
                        .defaultSuccessUrl("/todo.html", true)
                        // Set URL to redirect to on login failure
                        .failureUrl("/login.html?error=true")
                        .permitAll()
                )
                // Configure logout behavior
                .logout(logout -> logout
                        // Set logout URL
                        .logoutUrl("/logout")
                        // Set URL to redirect to after successful logout
                        .logoutSuccessUrl("/login.html")
                        .permitAll()
                );

        // Build and return the configured SecurityFilterChain
        return http.build();
    }

}
