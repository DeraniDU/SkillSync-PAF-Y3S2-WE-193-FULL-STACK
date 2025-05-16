package com.example.skill_catlog.controller;

import com.example.skill_catlog.dto.ContributorDTO;
import com.example.skill_catlog.dto.ProjectDTO;
import com.example.skill_catlog.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO createdProject = projectService.createProject(projectDTO);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<ProjectDTO> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // Get project by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable String id) {
        ProjectDTO project = projectService.getProjectById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    // Get projects by type
    @GetMapping("/type/{projectType}")
    public ResponseEntity<List<ProjectDTO>> getProjectsByType(@PathVariable String projectType) {
        List<ProjectDTO> projects = projectService.getProjectsByType(projectType);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // Get projects by tech stack
    @GetMapping("/tech-stack")
    public ResponseEntity<List<ProjectDTO>> getProjectsByTechStack(
            @RequestParam List<String> techStack,
            @RequestParam(required = false, defaultValue = "false") boolean matchAll) {
        List<ProjectDTO> projects = projectService.getProjectsByTechStack(techStack, matchAll);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateProject(
            @PathVariable String id,
            @RequestBody ProjectDTO projectDTO) {
        ProjectDTO updatedProject = projectService.updateProject(id, projectDTO);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable String id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Add contributor to project
    @PostMapping("/{projectId}/contributors")
    public ResponseEntity<ProjectDTO> addContributor(
            @PathVariable String projectId,
            @RequestBody ContributorDTO contributorDTO) {
        ProjectDTO updatedProject = projectService.addContributor(projectId, contributorDTO);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    // Remove contributor from project
    @DeleteMapping("/{projectId}/contributors/{contributorId}")
    public ResponseEntity<ProjectDTO> removeContributor(
            @PathVariable String projectId,
            @PathVariable String contributorId) {
        ProjectDTO updatedProject = projectService.removeContributor(projectId, contributorId);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    // Get projects by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ProjectDTO>> getProjectsByStatus(@PathVariable String status) {
        List<ProjectDTO> projects = projectService.getProjectsByStatus(status);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
}