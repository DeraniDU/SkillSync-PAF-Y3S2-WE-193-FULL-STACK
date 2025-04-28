package com.SkillSync.user_services.service;

import com.SkillSync.user_services.config.*;
import com.SkillSync.user_services.dto.*;
import com.SkillSync.user_services.entity.*;
import com.SkillSync.user_services.entity.User;
import com.SkillSync.user_services.repo.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            @Lazy AuthenticationManager authenticationManager,
            JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    // Rest of the implementation remains the same
    // ...

    @Override
    public UserDto registerUser(UserRegistrationDto registrationDto) {
        // Validate input
        if (registrationDto == null) {
            throw new IllegalArgumentException("Registration data cannot be null");
        }

        // Check for existing users
        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Create and save user
        User user = User.builder()
                .username(registrationDto.getUsername().trim())
                .email(registrationDto.getEmail().trim().toLowerCase())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .role(registrationDto.getRole() != null ? registrationDto.getRole() : Role.USER)
                .provider("local")
                .build();

        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        if (userDto == null) {
            throw new IllegalArgumentException("User data cannot be null");
        }

        // Check for existing users
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Create and save user
        User user = User.builder()
                .username(userDto.getUsername().trim())
                .email(userDto.getEmail().trim().toLowerCase())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(userDto.getRole() != null ? userDto.getRole() : Role.USER)
                .provider("local")
                .build();

        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        // Authenticate credentials
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Get user details
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Generate JWT token
        String jwtToken = jwtService.generateToken(user);

        // Build response
        return LoginResponse.builder()
                .token(jwtToken)
                .username(user.getUsername())
                .user(convertToDto(user))
                .build();
    }

    @Override
    public UserDto getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return convertToDto(user);
    }

    // Helper methods
    private UserDto convertToDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );
    }

    @Override
    public User processOAuth2User(String provider, Map<String, Object> attributes) {
        String email = extractEmail(attributes, provider);
        String name = extractName(attributes, provider);
        String providerId = extractProviderId(attributes, provider);

        // Check if user exists with this provider
        Optional<User> userOptional = userRepository.findByProviderAndProviderId(provider, providerId);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }

        // Check if email is already registered
        if (email != null) {
            userOptional = userRepository.findByEmail(email);
            if (userOptional.isPresent()) {
                // Update existing user with OAuth info
                User existingUser = userOptional.get();
                existingUser.setProvider(provider);
                existingUser.setProviderId(providerId);
                return userRepository.save(existingUser);
            }
        }

        // Create new OAuth user
        User newUser = User.builder()
                .username(generateUniqueUsername(name))
                .email(email)
                .provider(provider)
                .providerId(providerId)
                .role(Role.USER)
                .password("") // OAuth users don't need a password
                .build();

        return userRepository.save(newUser);
    }

    private String extractEmail(Map<String, Object> attributes, String provider) {
        if ("google".equals(provider)) {
            return (String) attributes.get("email");
        } else if ("github".equals(provider)) {
            return (String) attributes.get("email");
        }
        return null;
    }

    private String extractName(Map<String, Object> attributes, String provider) {
        if ("google".equals(provider)) {
            return (String) attributes.get("name");
        } else if ("github".equals(provider)) {
            return (String) attributes.get("login");
        }
        return "user" + System.currentTimeMillis();
    }

    private String extractProviderId(Map<String, Object> attributes, String provider) {
        if ("google".equals(provider)) {
            return (String) attributes.get("sub");
        } else if ("github".equals(provider)) {
            return attributes.get("id").toString();
        }
        return null;
    }

    private String generateUniqueUsername(String baseName) {
        String username = baseName;
        int counter = 1;

        while (userRepository.existsByUsername(username)) {
            username = baseName + counter++;
        }

        return username;
    }
}
