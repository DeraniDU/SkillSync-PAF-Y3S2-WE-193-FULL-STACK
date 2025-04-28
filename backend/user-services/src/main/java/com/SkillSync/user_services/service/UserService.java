package com.SkillSync.user_services.service;

import com.SkillSync.user_services.dto.*;
import com.SkillSync.user_services.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;

public interface UserService extends UserDetailsService {
    UserDto registerUser(UserRegistrationDto registrationDto);
    UserDto registerUser(UserDto userDto);
    LoginResponse loginUser(LoginRequest loginRequest);
    UserDto getCurrentUser();
    User processOAuth2User(String provider, Map<String, Object> attributes);
}