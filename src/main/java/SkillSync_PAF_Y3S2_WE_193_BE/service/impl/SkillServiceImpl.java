package SkillSync_PAF_Y3S2_WE_193_BE.service.impl;

import SkillSync_PAF_Y3S2_WE_193_BE.model.*;
import SkillSync_PAF_Y3S2_WE_193_BE.repository.*;
import SkillSync_PAF_Y3S2_WE_193_BE.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Override
    public Skill createSkill(Skill skill) {
        if (skillRepository.existsByName(skill.getName())) {
            throw new IllegalArgumentException("Skill already exists with name: " + skill.getName());
        }
        return skillRepository.save(skill);
    }

    @Override
    public Skill getSkillById(Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Skill not found with ID: " + id));
    }

    @Override
    public Skill getSkillByName(String name) {
        return skillRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Skill not found with name: " + name));
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public List<Skill> getSkillsByCategory(Skill.SkillCategory category) {
        return skillRepository.findByCategory(category);
    }

    @Override
    public Skill updateSkill(Long id, Skill skillDetails) {
        Skill existingSkill = getSkillById(id);

        if (!existingSkill.getName().equals(skillDetails.getName()) &&
                skillRepository.existsByName(skillDetails.getName())) {
            throw new IllegalArgumentException("Skill name already exists: " + skillDetails.getName());
        }

        existingSkill.setName(skillDetails.getName());
        existingSkill.setDescription(skillDetails.getDescription());
        existingSkill.setCategory(skillDetails.getCategory());

        return skillRepository.save(existingSkill);
    }

    @Override
    public void deleteSkill(Long id) {
        Skill skill = getSkillById(id);
        skillRepository.delete(skill);
    }

    @Override
    public Skill addUserToSkill(Long skillId, Long userId) {
        Skill skill = getSkillById(skillId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        skill.getUsers().add(user);
        user.getSkills().add(skill);
        return skillRepository.save(skill);
    }

    @Override
    public Skill addProjectToSkill(Long skillId, Long projectId) {
        Skill skill = getSkillById(skillId);
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with ID: " + projectId));

        skill.getProjects().add(project);
        project.getRequiredSkills().add(skill);
        return skillRepository.save(skill);
    }
}