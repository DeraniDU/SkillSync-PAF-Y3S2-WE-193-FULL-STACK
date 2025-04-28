package com.SkillSync.user_services.dto;

import com.SkillSync.user_services.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private String email;
    private String password;  // Added temporarily
    private Role role;
}