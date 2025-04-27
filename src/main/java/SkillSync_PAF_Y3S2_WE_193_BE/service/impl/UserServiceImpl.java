package SkillSync_PAF_Y3S2_WE_193_BE.service.impl;

import SkillSync_PAF_Y3S2_WE_193_BE.model.*;
import SkillSync_PAF_Y3S2_WE_193_BE.repository.*;
import SkillSync_PAF_Y3S2_WE_193_BE.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already taken");
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + username));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User updateUser(Long id, User userDetails) {
        User existingUser = getUserById(id);

        if (!existingUser.getEmail().equals(userDetails.getEmail()) &&
                userRepository.existsByEmail(userDetails.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        if (!existingUser.getUsername().equals(userDetails.getUsername()) &&
                userRepository.existsByUsername(userDetails.getUsername())) {
            throw new IllegalArgumentException("Username already taken");
        }

        existingUser.setUsername(userDetails.getUsername());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setBio(userDetails.getBio());
        existingUser.setRole(userDetails.getRole());

        if (userDetails.getPasswordHash() != null && !userDetails.getPasswordHash().isEmpty()) {
            existingUser.setPasswordHash(userDetails.getPasswordHash());
        }

        return userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = getUserById(id);

        // Remove user from all projects they collaborate on
        List<Project> projects = projectRepository.findByCollaboratorsContaining(user);
        projects.forEach(project -> {
            project.getCollaborators().remove(user);
            projectRepository.save(project);
        });

        // Clear user's skills
        user.getSkills().clear();
        userRepository.save(user);

        // Finally delete the user
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public User addSkillToUser(Long userId, Long skillId) {
        User user = getUserById(userId);
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new IllegalArgumentException("Skill not found with ID: " + skillId));

        if (user.getSkills().contains(skill)) {
            throw new IllegalStateException("User already has this skill");
        }

        user.addSkill(skill);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User removeSkillFromUser(Long userId, Long skillId) {
        User user = getUserById(userId);
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new IllegalArgumentException("Skill not found with ID: " + skillId));

        if (!user.getSkills().contains(skill)) {
            throw new IllegalStateException("User doesn't have this skill");
        }

        user.removeSkill(skill);
        return userRepository.save(user);
    }

    @Override
    public List<Project> getUserProjects(Long userId) {
        User user = getUserById(userId);
        return projectRepository.findByCollaboratorsContaining(user);
    }

    @Override
    public List<Skill> getUserSkills(Long userId) {
        User user = getUserById(userId);
        return List.copyOf(user.getSkills());
    }
}