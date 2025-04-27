package SkillSync_PAF_Y3S2_WE_193_BE.service.impl;

import SkillSync_PAF_Y3S2_WE_193_BE.model.Skill;
import SkillSync_PAF_Y3S2_WE_193_BE.repository.SkillRepository;
import SkillSync_PAF_Y3S2_WE_193_BE.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    @Override
    @Transactional
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
    @Transactional
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
    @Transactional
    public void deleteSkill(Long id) {
        Skill skill = getSkillById(id);
        skillRepository.delete(skill);
    }
}