package com.example.skill_catlog.repository;

import com.example.skill_catlog.model.CollaborationRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollaborationRequestRepository extends MongoRepository<CollaborationRequest, String> {

    List<CollaborationRequest> findByProjectId(String projectId);

    List<CollaborationRequest> findByProjectIdAndStatus(String projectId, String status);

    List<CollaborationRequest> findByEmail(String email);
}