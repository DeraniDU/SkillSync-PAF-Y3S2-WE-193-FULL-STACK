package com.SkillSync.user_services.config;

import com.SkillSync.user_services.dto.OAuth2Response;
import com.SkillSync.user_services.entity.User;
import com.SkillSync.user_services.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserService userService;
    private final JwtService jwtService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Constructor with @Lazy for UserService to break circular dependency
    public OAuth2LoginSuccessHandler(@Lazy UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String provider = determineProvider(oAuth2User);
        User user = userService.processOAuth2User(provider, oAuth2User.getAttributes());

        String jwtToken = jwtService.generateToken(user);

        OAuth2Response oauthResponse = new OAuth2Response();
        oauthResponse.setUsername(user.getUsername());
        oauthResponse.setEmail(user.getEmail());
        oauthResponse.setRole(user.getRole());
        oauthResponse.setToken(jwtToken);

        response.setContentType("application/json");
        response.getWriter().write(convertObjectToJson(oauthResponse));
        response.getWriter().flush();
    }

    private String determineProvider(OAuth2User oAuth2User) {
        // Logic to determine provider based on attributes
        if (oAuth2User.getAttributes().containsKey("sub")) { // Google
            return "google";
        } else if (oAuth2User.getAttributes().containsKey("id")) { // GitHub
            return "github";
        }
        return "unknown";
    }

    private String convertObjectToJson(Object object) throws IOException {
        return objectMapper.writeValueAsString(object);
    }
}
