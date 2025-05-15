package com.SkillSync.notification_management.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/ws/**").authenticated()  // Secure WebSocket handshake endpoint
                        .anyRequest().permitAll()                    // Allow all other requests
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Your custom login page (optional)
                        .permitAll()
                )
                .httpBasic(withDefaults())  // Enable HTTP Basic auth
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/ws/**") // Disable CSRF for WebSocket endpoints
                );

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("user1")
                .password("password")
                .roles("USER")
                .build();

        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("user2")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}
