package com.example.skill_catlog.repository;

import com.example.skill_catlog.model.SkillCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SkillCategoryRepository extends MongoRepository<SkillCategory, String> {
    Optional<SkillCategory> findById(String id);
}