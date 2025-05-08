package com.example.skill_catlog.controller;

import com.example.skill_catlog.dto.ContributorDTO;
import com.example.skill_catlog.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contributors")
public class ContributorController {

    @Autowired
    private ContributorService contributorService;

    // Create a new contributor
    @PostMapping
    public ResponseEntity<ContributorDTO> createContributor(@RequestBody ContributorDTO contributorDTO) {
        ContributorDTO createdContributor = contributorService.createContributor(contributorDTO);
        return new ResponseEntity<>(createdContributor, HttpStatus.CREATED);
    }

    // Get all contributors
    @GetMapping
    public ResponseEntity<List<ContributorDTO>> getAllContributors() {
        List<ContributorDTO> contributors = contributorService.getAllContributors();
        return new ResponseEntity<>(contributors, HttpStatus.OK);
    }

    // Get contributor by ID
    @GetMapping("/{id}")
    public ResponseEntity<ContributorDTO> getContributorById(@PathVariable String id) {
        ContributorDTO contributor = contributorService.getContributorById(id);
        return new ResponseEntity<>(contributor, HttpStatus.OK);
    }

    // Get contributor by email
    @GetMapping("/email/{email}")
    public ResponseEntity<ContributorDTO> getContributorByEmail(@PathVariable String email) {
        ContributorDTO contributor = contributorService.getContributorByEmail(email);
        return new ResponseEntity<>(contributor, HttpStatus.OK);
    }

    // Update contributor
    @PutMapping("/{id}")
    public ResponseEntity<ContributorDTO> updateContributor(
            @PathVariable String id,
            @RequestBody ContributorDTO contributorDTO) {
        ContributorDTO updatedContributor = contributorService.updateContributor(id, contributorDTO);
        return new ResponseEntity<>(updatedContributor, HttpStatus.OK);
    }

    // Partial update contributor
    @PatchMapping("/{id}")
    public ResponseEntity<ContributorDTO> partialUpdateContributor(
            @PathVariable String id,
            @RequestBody ContributorDTO contributorDTO) {
        ContributorDTO updatedContributor = contributorService.updateContributor(id, contributorDTO);
        return new ResponseEntity<>(updatedContributor, HttpStatus.OK);
    }

    // Delete contributor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContributor(@PathVariable String id) {
        contributorService.deleteContributor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Search contributors by skills (comma-separated)
    @GetMapping("/search")
    public ResponseEntity<List<ContributorDTO>> searchContributorsBySkills(
            @RequestParam String skills) {
        // Implementation would need to be added to the service layer
        // This is just a placeholder for the endpoint
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    // Get contributors by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ContributorDTO>> getContributorsByStatus(
            @PathVariable String status) {
        // Implementation would need to be added to the service layer
        // This is just a placeholder for the endpoint
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    // Get contributors by role
    @GetMapping("/role/{role}")
    public ResponseEntity<List<ContributorDTO>> getContributorsByRole(
            @PathVariable String role) {
        // Implementation would need to be added to the service layer
        // This is just a placeholder for the endpoint
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}