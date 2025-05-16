package com.example.skill_catlog.controller;

import com.example.skill_catlog.dto.SkillRequest;
import com.example.skill_catlog.dto.SkillResponse;
import com.example.skill_catlog.service.SkillService;
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
    public ResponseEntity<SkillResponse> createSkill(@Valid @RequestBody SkillRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(skillService.createSkill(request));
    }

    @GetMapping("/search")
    public ResponseEntity<List<SkillResponse>> searchSkills(
            @RequestParam String query) {
        return ResponseEntity.ok(skillService.searchSkills(query));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<SkillResponse>> getSkillsByCategory(
            @PathVariable String categoryId) {
        return ResponseEntity.ok(skillService.getSkillsByCategory(categoryId));
    }

    // Test endpoint
    @GetMapping("/ping")
    public String ping() {
        return "pong - " + System.currentTimeMillis();
    }

    // Get skill by ID
    @GetMapping("/{id}")
    public ResponseEntity<SkillResponse> getSkillById(@PathVariable String id) {
        return ResponseEntity.ok(skillService.getSkillById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<SkillResponse> updateSkill(
            @PathVariable String id,
            @Valid @RequestBody SkillRequest request) {
        return ResponseEntity.ok(skillService.updateSkill(id, request));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable String id) {
        skillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }
}