package SkillSync_PAF_Y3S2_WE_193_BE.controller;

import SkillSync_PAF_Y3S2_WE_193_BE.model.Skill;
import SkillSync_PAF_Y3S2_WE_193_BE.service.SkillService; // Add this import
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {
    // Rest of your controller code remains the same
}