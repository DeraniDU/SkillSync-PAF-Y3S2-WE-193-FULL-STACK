package com.SkillSync.notification_management.WebConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configure CORS mappings to allow cross-origin requests
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow all paths under '/api/**' to accept requests from 'http://localhost:3000'
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000") // Frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
                .allowedHeaders("*") // Allow any headers
                .allowCredentials(true) // Allow credentials such as cookies
                .maxAge(3600); // Cache CORS pre-flight response for 1 hour
    }

    // You can add more custom configurations if required
}
