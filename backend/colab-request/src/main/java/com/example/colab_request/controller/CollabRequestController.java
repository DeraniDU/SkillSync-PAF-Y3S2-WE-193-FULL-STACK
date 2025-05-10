// src/main/java/com/example/colab_request/controller/CollabRequestController.java
package com.example.colab_request.controller;

import com.example.colab_request.model.CollabRequest;
import com.example.colab_request.service.CollabRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/collab-requests")
public class CollabRequestController {

    @Autowired
    private CollabRequestService service;

    @PostMapping
    public ResponseEntity<CollabRequest> createRequest(@RequestBody CollabRequest request) {
        try {
            CollabRequest createdRequest = service.createRequest(request);
            return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<CollabRequest>> getRequestsForProject(@PathVariable String projectId) {
        try {
            List<CollabRequest> requests = service.getRequestsForProject(projectId);
            return new ResponseEntity<>(requests, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollabRequest> getRequestById(@PathVariable String id) {
        try {
            CollabRequest request = service.getRequestById(id);
            return request != null
                    ? new ResponseEntity<>(request, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}/why")
    public ResponseEntity<CollabRequest> updateWhyCollaborate(
            @PathVariable String id,
            @RequestBody String newWhy) {
        try {
            CollabRequest updatedRequest = service.updateWhyCollaborate(id, newWhy);
            return updatedRequest != null
                    ? new ResponseEntity<>(updatedRequest, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
