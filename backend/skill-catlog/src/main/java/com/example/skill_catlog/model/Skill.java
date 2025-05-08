package com.example.skill_catlog.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection = "skills")
@Data
public class Skill {
    @Id
    private String id;
    private String name;
    private String description;

    @DBRef
    private SkillCategory category;
}