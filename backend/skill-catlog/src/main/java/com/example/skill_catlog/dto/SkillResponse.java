package com.example.skill_catlog.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SkillResponse {
    private String id;
    private String name;
    private String description;
    private String category;
}