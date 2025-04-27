package SkillSync_PAF_Y3S2_WE_193_BE.controller;

import SkillSync_PAF_Y3S2_WE_193_BE.dto.SkillDTO;
import SkillSync_PAF_Y3S2_WE_193_BE.model.Skill;
import SkillSync_PAF_Y3S2_WE_193_BE.service.SkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @PostMapping
    public ResponseEntity<SkillDTO> createSkill(@RequestBody @Valid Skill skill) {
        Skill createdSkill = skillService.createSkill(skill);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SkillDTO.fromEntity(createdSkill));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillDTO> getSkillById(@PathVariable Long id) {
        return ResponseEntity.ok(
                SkillDTO.fromEntity(skillService.getSkillById(id))
        );
    }

    @GetMapping
    public ResponseEntity<List<SkillDTO>> getAllSkills() {
        return ResponseEntity.ok(
                skillService.getAllSkills().stream()
                        .map(SkillDTO::fromEntity)
                        .toList()
        );
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<SkillDTO>> getSkillsByCategory(
            @PathVariable Skill.SkillCategory category) {
        return ResponseEntity.ok(
                skillService.getSkillsByCategory(category).stream()
                        .map(SkillDTO::fromEntity)
                        .toList()
        );
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<SkillDTO> getSkillByName(@PathVariable String name) {
        return ResponseEntity.ok(
                SkillDTO.fromEntity(skillService.getSkillByName(name))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillDTO> updateSkill(
            @PathVariable Long id,
            @RequestBody @Valid Skill skillDetails) {
        return ResponseEntity.ok(
                SkillDTO.fromEntity(skillService.updateSkill(id, skillDetails))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{skillId}/users/{userId}")
    public ResponseEntity<SkillDTO> addUserToSkill(
            @PathVariable Long skillId,
            @PathVariable Long userId) {
        return ResponseEntity.ok(
                SkillDTO.fromEntity(skillService.addUserToSkill(skillId, userId))
        );
    }

    @PostMapping("/{skillId}/projects/{projectId}")
    public ResponseEntity<SkillDTO> addProjectToSkill(
            @PathVariable Long skillId,
            @PathVariable Long projectId) {
        return ResponseEntity.ok(
                SkillDTO.fromEntity(skillService.addProjectToSkill(skillId, projectId))
        );
    }
}