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
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

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

        Optional<User> userOptional = userService.findByGoogleId(googleId);
        User user;

        if (userOptional.isPresent()) {
            // Update existing user
            user = userOptional.get();
            user.setName(name);
            user.setPicture(picture);
            user.setUpdatedAt(LocalDateTime.now());
        } else {
            // Create new user
            user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setPicture(picture);
            user.setGoogleId(googleId);
            user.setRoles(new HashSet<>(Collections.singletonList("ROLE_USER")));
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
        }

        User savedUser = userService.save(user);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                attributes,
                "email");
    }
}
