package com.SkillSync.user_services.dto;



import com.SkillSync.user_services.entity.Role;
import lombok.Data;

@Data
public class OAuth2Response {
    private String username;
    private String email;
    private Role role;
    private String token;
}