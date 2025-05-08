package com.example.skill_catlog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SkillRequest {
    @NotBlank(message = "Skill name cannot be blank")
    private String name;
    private String description;
    private String categoryId;
}