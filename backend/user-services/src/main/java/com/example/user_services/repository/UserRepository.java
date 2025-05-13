package com.example.user_services.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.user_services.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByProviderAndProviderId(String provider, String providerId);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
