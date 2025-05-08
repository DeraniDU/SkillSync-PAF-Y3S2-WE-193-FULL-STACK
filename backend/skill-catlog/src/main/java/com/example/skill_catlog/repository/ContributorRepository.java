package com.example.skill_catlog.repository;

import com.example.skill_catlog.model.Contributor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContributorRepository extends MongoRepository<Contributor, String> {
    Optional<Contributor> findByEmail(String email);
}