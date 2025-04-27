package SkillSync_PAF_Y3S2_WE_193_BE.service;

import SkillSync_PAF_Y3S2_WE_193_BE.model.Skill;
import java.util.List;

public interface SkillService {
    Skill createSkill(Skill skill);
    Skill getSkillById(Long id);
    Skill getSkillByName(String name);
    List<Skill> getAllSkills();
    List<Skill> getSkillsByCategory(Skill.SkillCategory category);
    Skill updateSkill(Long id, Skill skillDetails);
    void deleteSkill(Long id);
    Skill addUserToSkill(Long skillId, Long userId);
    Skill addProjectToSkill(Long skillId, Long projectId);
}