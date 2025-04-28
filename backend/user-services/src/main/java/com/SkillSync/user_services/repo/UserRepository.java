package com.SkillSync.user_services.repo;

import com.SkillSync.user_services.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    // Find user by username
    Optional<User> findByUsername(String username);

    // Find user by email
    Optional<User> findByEmail(String email);

    // Find user by provider and providerId (for OAuth2 users)
    Optional<User> findByProviderAndProviderId(String provider, String providerId);

    // Check if username exists
    boolean existsByUsername(String username);

    // Check if email exists
    boolean existsByEmail(String email);
}