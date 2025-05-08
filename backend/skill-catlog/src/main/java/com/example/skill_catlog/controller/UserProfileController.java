package com.example.skill_catlog.controller;

import com.example.skill_catlog.dto.SkillMatchResponseDTO;
import com.example.skill_catlog.model.UserProfile;
import com.example.skill_catlog.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-profiles")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    // Create a new user profile
    @PostMapping
    public ResponseEntity<UserProfile> createUserProfile(@RequestBody UserProfile userProfile) {
        UserProfile createdProfile = userProfileService.createUserProfile(userProfile);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

    // Get all user profiles
    @GetMapping
    public ResponseEntity<List<UserProfile>> getAllUserProfiles() {
        List<UserProfile> userProfiles = userProfileService.getAllUserProfiles();
        return new ResponseEntity<>(userProfiles, HttpStatus.OK);
    }

    // Get user profile by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getUserProfileById(@PathVariable String id) {
        UserProfile userProfile = userProfileService.getUserProfileById(id);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    // Get user profile by username
    @GetMapping("/username/{username}")
    public ResponseEntity<UserProfile> getUserProfileByUsername(@PathVariable String username) {
        UserProfile userProfile = userProfileService.getUserProfileByUsername(username);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    // Get user profile by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserProfile> getUserProfileByEmail(@PathVariable String email) {
        UserProfile userProfile = userProfileService.getUserProfileByEmail(email);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    // Update user profile
    @PutMapping("/{id}")
    public ResponseEntity<UserProfile> updateUserProfile(
            @PathVariable String id,
            @RequestBody UserProfile userProfileDetails) {
        UserProfile updatedProfile = userProfileService.updateUserProfile(id, userProfileDetails);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    // Delete user profile
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable String id) {
        userProfileService.deleteUserProfile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get matching projects for user skills
    @GetMapping("/{userId}/skill-matches")
    public ResponseEntity<List<SkillMatchResponseDTO>> findMatchingProjects(@PathVariable String userId) {
        List<SkillMatchResponseDTO> matches = userProfileService.findMatchingProjects(userId);
        return new ResponseEntity<>(matches, HttpStatus.OK);
    }

    // Add project to user profile
    @PostMapping("/{userId}/projects/{projectId}")
    public ResponseEntity<Void> addProjectToUserProfile(
            @PathVariable String userId,
            @PathVariable String projectId) {
        userProfileService.addProjectToUserProfile(userId, projectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Remove project from user profile
    @DeleteMapping("/{userId}/projects/{projectId}")
    public ResponseEntity<Void> removeProjectFromUserProfile(
            @PathVariable String userId,
            @PathVariable String projectId) {
        userProfileService.removeProjectFromUserProfile(userId, projectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Search user profiles by skills
    @GetMapping("/search")
    public ResponseEntity<List<UserProfile>> searchBySkills(@RequestParam List<String> skills) {
        // Implementation would need to be added to service layer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    // Get user profiles with specific skill
    @GetMapping("/skill/{skill}")
    public ResponseEntity<List<UserProfile>> getUsersBySkill(@PathVariable String skill) {
        // Implementation would need to be added to service layer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}