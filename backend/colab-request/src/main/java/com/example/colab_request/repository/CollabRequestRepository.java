// File: src/main/java/com/example/colab_request/repository/CollabRequestRepository.java
package com.example.colab_request.repository;

import com.example.colab_request.model.CollabRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CollabRequestRepository extends MongoRepository<CollabRequest, String> {
    List<CollabRequest> findByProjectId(String projectId);
}
