package com.example.user_services.config;

//ths is a dbms
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.user_services.models.ERole;
import com.example.user_services.models.Role;
import com.example.user_services.repository.RoleRepository;
import org.springframework.context.event.EventListener;

@Configuration
public class DatabaseInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeRoles() {
        // Check if roles collection is empty
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role(ERole.ROLE_USER));
            roleRepository.save(new Role(ERole.ROLE_MODERATOR));
            roleRepository.save(new Role(ERole.ROLE_ADMIN));
            System.out.println("MongoDB: Roles initialized");
        }
    }
}

