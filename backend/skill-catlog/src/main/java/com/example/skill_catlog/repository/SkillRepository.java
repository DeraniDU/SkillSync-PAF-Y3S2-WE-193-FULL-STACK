package com.example.skill_catlog.repository;

import com.example.skill_catlog.model.Skill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SkillRepository extends MongoRepository<Skill, String> {
    List<Skill> findByNameContainingIgnoreCase(String query);
    List<Skill> findByCategoryId(String categoryId);
}