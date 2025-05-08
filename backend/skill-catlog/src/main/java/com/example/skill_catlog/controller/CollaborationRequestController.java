package com.example.skill_catlog.controller;

import com.example.skill_catlog.dto.CollaborationRequestDTO;
import com.example.skill_catlog.service.CollaborationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collaboration-requests")
public class CollaborationRequestController {

    @Autowired
    private CollaborationRequestService collaborationRequestService;

    // Create a new collaboration request
    @PostMapping
    public ResponseEntity<CollaborationRequestDTO> createCollaborationRequest(
            @RequestBody CollaborationRequestDTO collaborationRequestDTO) {
        CollaborationRequestDTO createdRequest = collaborationRequestService.createCollaborationRequest(collaborationRequestDTO);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    // Get a specific collaboration request by ID
    @GetMapping("/{id}")
    public ResponseEntity<CollaborationRequestDTO> getCollaborationRequestById(@PathVariable String id) {
        CollaborationRequestDTO request = collaborationRequestService.getCollaborationRequestById(id);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    // Get all collaboration requests for a specific project
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<CollaborationRequestDTO>> getCollaborationRequestsByProjectId(
            @PathVariable String projectId) {
        List<CollaborationRequestDTO> requests = collaborationRequestService.getCollaborationRequestsByProjectId(projectId);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    // Get collaboration requests for a project filtered by status
    @GetMapping("/project/{projectId}/status/{status}")
    public ResponseEntity<List<CollaborationRequestDTO>> getCollaborationRequestsByProjectIdAndStatus(
            @PathVariable String projectId,
            @PathVariable String status) {
        List<CollaborationRequestDTO> requests = collaborationRequestService
                .getCollaborationRequestsByProjectIdAndStatus(projectId, status);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    // Get all collaboration requests by a specific email (user)
    @GetMapping("/user/{email}")
    public ResponseEntity<List<CollaborationRequestDTO>> getCollaborationRequestsByEmail(
            @PathVariable String email) {
        List<CollaborationRequestDTO> requests = collaborationRequestService.getCollaborationRequestsByEmail(email);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    // Update the status of a collaboration request
    @PatchMapping("/{id}/status")
    public ResponseEntity<CollaborationRequestDTO> updateCollaborationRequestStatus(
            @PathVariable String id,
            @RequestParam String status) {
        CollaborationRequestDTO updatedRequest = collaborationRequestService.updateCollaborationRequestStatus(id, status);
        return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
    }

    // Additional endpoints could include:

    // Get all pending collaboration requests
    @GetMapping("/pending")
    public ResponseEntity<List<CollaborationRequestDTO>> getAllPendingRequests() {
        List<CollaborationRequestDTO> requests = collaborationRequestService
                .getCollaborationRequestsByProjectIdAndStatus(null, "Pending");
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    // Get count of collaboration requests by status for a project
    @GetMapping("/project/{projectId}/count")
    public ResponseEntity<Long> getCollaborationRequestCountByProjectId(
            @PathVariable String projectId,
            @RequestParam(required = false) String status) {
        long count;
        if (status != null) {
            count = collaborationRequestService.getCollaborationRequestsByProjectIdAndStatus(projectId, status).size();
        } else {
            count = collaborationRequestService.getCollaborationRequestsByProjectId(projectId).size();
        }
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}