// File: src/main/java/com/example/colab_request/controller/CollabRequestController.java
package com.example.colab_request.controller;

import com.example.colab_request.model.CollabRequest;
import com.example.colab_request.service.CollabRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collab-requests")
public class CollabRequestController {
    @Autowired
    private CollabRequestService service;

    // Submit a new collaboration request
    @PostMapping
    public CollabRequest create(@RequestBody CollabRequest request) {
        return service.createRequest(request);
    }

    // Get all requests for a project
    @GetMapping("/project/{projectId}")
    public List<CollabRequest> getForProject(@PathVariable String projectId) {
        return service.getRequestsForProject(projectId);
    }

    // Get a single request
    @GetMapping("/{id}")
    public CollabRequest getById(@PathVariable String id) {
        return service.getRequestById(id);
    }

    // Update only the "whyCollaborate" field
    @PatchMapping("/{id}/why")
    public CollabRequest updateWhy(@PathVariable String id, @RequestBody String newWhy) {
        return service.updateWhyCollaborate(id, newWhy);
    }
}
