package com.SkillSync.user_services.dto;

import com.SkillSync.user_services.entity.Role;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
public class UserRegistrationDto {
    @NotBlank @Size(min=3, max=20)
    private String username;

    @NotBlank @Email
    private String email;

    @NotBlank @Size(min=6)
    private String password;

    private Role role = Role.USER;
}
