package com.example.skill_catlog.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "categories")
@Data
public class SkillCategory {
    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("skills")
    private Set<Skill> skills = new HashSet<>();
}