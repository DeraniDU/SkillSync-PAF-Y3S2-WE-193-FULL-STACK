// File: src/main/java/com/example/colab_request/service/CollabRequestService.java
package com.example.colab_request.service;

import com.example.colab_request.model.CollabRequest;
import com.example.colab_request.repository.CollabRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CollabRequestService {
    @Autowired
    private CollabRequestRepository repository;

    public CollabRequest createRequest(CollabRequest request) {
        request.setCreatedAt(new Date());
        request.setUpdatedAt(new Date());
        return repository.save(request);
    }

    public List<CollabRequest> getRequestsForProject(String projectId) {
        return repository.findByProjectId(projectId);
    }

    public CollabRequest getRequestById(String id) {
        return repository.findById(id).orElse(null);
    }

    // Only update the "whyCollaborate" field
    public CollabRequest updateWhyCollaborate(String id, String newWhy) {
        CollabRequest req = repository.findById(id).orElseThrow(() -> new RuntimeException("Request not found"));
        req.setWhyCollaborate(newWhy);
        req.setUpdatedAt(new Date());
        return repository.save(req);
    }
}
