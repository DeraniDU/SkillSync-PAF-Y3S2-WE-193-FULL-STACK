package SkillSync_PAF_Y3S2_WE_193_BE.service;

import SkillSync_PAF_Y3S2_WE_193_BE.model.*;
import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
    User addSkillToUser(Long userId, Long skillId);
    User removeSkillFromUser(Long userId, Long skillId);
    List<Project> getUserProjects(Long userId);
    List<Skill> getUserSkills(Long userId);
}