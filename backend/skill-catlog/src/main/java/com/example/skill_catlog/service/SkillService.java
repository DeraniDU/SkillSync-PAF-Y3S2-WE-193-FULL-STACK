package com.example.skill_catlog.service;

import com.example.skill_catlog.dto.SkillRequest;
import com.example.skill_catlog.dto.SkillResponse;
import com.example.skill_catlog.exception.ResourceNotFoundException;
import com.example.skill_catlog.model.Skill;
import com.example.skill_catlog.model.SkillCategory;
import com.example.skill_catlog.repository.SkillCategoryRepository;
import com.example.skill_catlog.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillService {
    private final SkillRepository skillRepository;
    private final SkillCategoryRepository categoryRepository;

    public SkillResponse createSkill(SkillRequest request) {
        SkillCategory category = request.getCategoryId() != null ?
                categoryRepository.findById(request.getCategoryId())
                        .orElseThrow(() -> new ResourceNotFoundException("Category not found")) :
                null;

        Skill skill = new Skill();
        skill.setName(request.getName());
        skill.setDescription(request.getDescription());
        skill.setCategory(category);

        Skill savedSkill = skillRepository.save(skill);
        return mapToSkillResponse(savedSkill);
    }

    public List<SkillResponse> searchSkills(String query) {
        return skillRepository.findByNameContainingIgnoreCase(query)
                .stream()
                .map(this::mapToSkillResponse)
                .collect(Collectors.toList());
    }

    public List<SkillResponse> getSkillsByCategory(String categoryId) {
        return skillRepository.findByCategoryId(categoryId)
                .stream()
                .map(this::mapToSkillResponse)
                .collect(Collectors.toList());
    }

    public SkillResponse getSkillById(String id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
        return mapToSkillResponse(skill);
    }

    public SkillResponse updateSkill(String id, SkillRequest request) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));

        SkillCategory category = request.getCategoryId() != null ?
                categoryRepository.findById(request.getCategoryId())
                        .orElseThrow(() -> new ResourceNotFoundException("Category not found")) :
                null;

        skill.setName(request.getName());
        skill.setDescription(request.getDescription());
        skill.setCategory(category);

        Skill updatedSkill = skillRepository.save(skill);
        return mapToSkillResponse(updatedSkill);
    }

    public void deleteSkill(String id) {
        if (!skillRepository.existsById(id)) {
            throw new ResourceNotFoundException("Skill not found with id: " + id);
        }
        skillRepository.deleteById(id);
    }

    private SkillResponse mapToSkillResponse(Skill skill) {
        return SkillResponse.builder()
                .id(skill.getId())
                .name(skill.getName())
                .description(skill.getDescription())
                .category(skill.getCategory() != null ? skill.getCategory().getName() : null)
                .build();
    }
}