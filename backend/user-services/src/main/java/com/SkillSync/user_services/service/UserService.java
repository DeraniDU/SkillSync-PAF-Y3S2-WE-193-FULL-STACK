package com.SkillSync.user_services.service;

import com.SkillSync.user_services.dto.UserDTO;
import com.SkillSync.user_services.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    Optional<User> findByGoogleId(String googleId);

    List<UserDTO> findAll();

    void delete(String id);

    UserDTO update(String id, UserDTO userDTO);
}
