package com.example.skill_catlog.service;

import com.example.skill_catlog.dto.ProjectDTO;
import com.example.skill_catlog.dto.SkillMatchResponseDTO;
import com.example.skill_catlog.exception.ResourceNotFoundException;
import com.example.skill_catlog.model.Project;
import com.example.skill_catlog.model.UserProfile;
import com.example.skill_catlog.mapper.ProjectMapper;
import com.example.skill_catlog.repository.ProjectRepository;
import com.example.skill_catlog.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;

    public UserProfile createUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    public UserProfile getUserProfileById(String id) {
        return userProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User profile not found with id: " + id));
    }

    public UserProfile getUserProfileByUsername(String username) {
        return userProfileRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User profile not found with username: " + username));
    }

    public UserProfile getUserProfileByEmail(String email) {
        return userProfileRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User profile not found with email: " + email));
    }

    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    public UserProfile updateUserProfile(String id, UserProfile userProfileDetails) {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User profile not found with id: " + id));

        if (userProfileDetails.getUsername() != null) {
            userProfile.setUsername(userProfileDetails.getUsername());
        }
        if (userProfileDetails.getFullName() != null) {
            userProfile.setFullName(userProfileDetails.getFullName());
        }
        if (userProfileDetails.getEmail() != null) {
            userProfile.setEmail(userProfileDetails.getEmail());
        }
        if (userProfileDetails.getGithubProfile() != null) {
            userProfile.setGithubProfile(userProfileDetails.getGithubProfile());
        }
        if (userProfileDetails.getLinkedinProfile() != null) {
            userProfile.setLinkedinProfile(userProfileDetails.getLinkedinProfile());
        }
        if (userProfileDetails.getSkills() != null) {
            userProfile.setSkills(userProfileDetails.getSkills());
        }

        return userProfileRepository.save(userProfile);
    }

    public void deleteUserProfile(String id) {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User profile not found with id: " + id));

        userProfileRepository.delete(userProfile);
    }

    public List<SkillMatchResponseDTO> findMatchingProjects(String userId) {
        UserProfile userProfile = userProfileRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User profile not found with id: " + userId));

        List<String> userSkills = userProfile.getSkills();
        List<Project> allProjects = projectRepository.findByStatus("Active");
        List<SkillMatchResponseDTO> matchedProjects = new ArrayList<>();

        for (Project project : allProjects) {
            List<String> projectTechStack = project.getTechStack();

            List<String> matchedSkills = userSkills.stream()
                    .filter(projectTechStack::contains)
                    .collect(Collectors.toList());

            List<String> missingSkills = projectTechStack.stream()
                    .filter(tech -> !userSkills.contains(tech))
                    .collect(Collectors.toList());

            int matchScore = matchedSkills.size();
            int maxScore = projectTechStack.size();
            int matchPercentage = (maxScore > 0) ? (matchScore * 100) / maxScore : 0;

            if (matchPercentage > 0) {
                SkillMatchResponseDTO matchResponse = new SkillMatchResponseDTO();
                matchResponse.setUserId(userId);
                matchResponse.setProjectId(project.getId());
                matchResponse.setMatchScore(matchScore);
                matchResponse.setMaxScore(maxScore);
                matchResponse.setMatchPercentage(matchPercentage);
                matchResponse.setProjectDetails(projectMapper.convertToDTO(project));
                matchResponse.setMatchedSkills(matchedSkills);
                matchResponse.setMissingSkills(missingSkills);

                matchedProjects.add(matchResponse);
            }
        }

        matchedProjects.sort((p1, p2) -> Integer.compare(p2.getMatchPercentage(), p1.getMatchPercentage()));

        return matchedProjects;
    }

    public void addProjectToUserProfile(String userId, String projectId) {
        UserProfile userProfile = userProfileRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User profile not found with id: " + userId));

        projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));

        if (!userProfile.getProjectIds().contains(projectId)) {
            userProfile.getProjectIds().add(projectId);
            userProfileRepository.save(userProfile);
        }
    }

    public void removeProjectFromUserProfile(String userId, String projectId) {
        UserProfile userProfile = userProfileRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User profile not found with id: " + userId));

        if (userProfile.getProjectIds().remove(projectId)) {
            userProfileRepository.save(userProfile);
        }
    }
}