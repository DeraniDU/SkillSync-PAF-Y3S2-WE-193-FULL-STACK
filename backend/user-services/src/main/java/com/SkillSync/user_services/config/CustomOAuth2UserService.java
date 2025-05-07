package com.SkillSync.user_services.config;

import com.SkillSync.user_services.model.User;
import com.SkillSync.user_services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        String picture = (String) attributes.get("picture");
        String googleId = (String) attributes.get("sub");

        // First try to find by googleId, then by email
        Optional<User> userOptional = userService.findByGoogleId(googleId);
        if (userOptional.isEmpty()) {
            userOptional = userService.findByEmail(email);
        }

        User user;

        if (userOptional.isPresent()) {
            // Update existing user
            user = userOptional.get();
            user.setName(name);
            user.setPicture(picture);
            user.setGoogleId(googleId);
            user.setUpdatedAt(LocalDateTime.now());
        } else {
            // Create new user
            user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setPicture(picture);
            user.setGoogleId(googleId);

// Use Set<String> to match User class
            Set<String> roles = new HashSet<>();
            roles.add("ROLE_USER");
            user.setRoles(roles);


            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
        }

        User savedUser = userService.save(user);

        return new DefaultOAuth2User(
                savedUser.getRoles().stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toSet()),
                attributes,
                "email");
    }
}


