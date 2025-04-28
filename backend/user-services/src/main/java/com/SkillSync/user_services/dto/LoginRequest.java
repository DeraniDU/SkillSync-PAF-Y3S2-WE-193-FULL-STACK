package com.SkillSync.user_services.dto;



import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}