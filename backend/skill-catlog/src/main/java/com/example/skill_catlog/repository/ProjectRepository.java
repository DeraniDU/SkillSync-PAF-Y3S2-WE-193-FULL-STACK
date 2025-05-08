package com.example.skill_catlog.repository;

import com.example.skill_catlog.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
    List<Project> findByProjectType(String projectType);

    @Query("{'techStack': {$all: ?0}}")
    List<Project> findByTechStackContainingAll(List<String> techStack);

    @Query("{'techStack': {$in: ?0}}")
    List<Project> findByTechStackContainingAny(List<String> techStack);

    List<Project> findByStatus(String status);  // This is the crucial method
}