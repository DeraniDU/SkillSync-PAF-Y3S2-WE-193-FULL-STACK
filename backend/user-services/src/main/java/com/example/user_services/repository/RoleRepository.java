package com.example.user_services.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.user_services.models.ERole;
import com.example.user_services.models.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
