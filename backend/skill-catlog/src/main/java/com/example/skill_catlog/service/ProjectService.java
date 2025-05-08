package com.example.skill_catlog.service;

import com.example.skill_catlog.dto.ContributorDTO;
import com.example.skill_catlog.dto.ProjectDTO;
import com.example.skill_catlog.exception.ResourceNotFoundException;
import com.example.skill_catlog.model.Contributor;
import com.example.skill_catlog.model.Project;
import com.example.skill_catlog.mapper.ProjectMapper;
import com.example.skill_catlog.repository.ContributorRepository;
import com.example.skill_catlog.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ContributorRepository contributorRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ProjectMapper projectMapper;

    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = projectMapper.convertToEntity(projectDTO);
        project.setCreatedAt(new Date());
        project.setUpdatedAt(new Date());
        project.setStatus("Active");

        Project savedProject = projectRepository.save(project);

        notificationService.createSystemNotification(
                "Project Created",
                "Your project has been successfully created.",
                "Project",
                savedProject.getId()
        );

        return projectMapper.convertToDTO(savedProject);
    }

    public ProjectDTO getProjectById(String id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        return projectMapper.convertToDTO(project);
    }

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(projectMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ProjectDTO> getProjectsByType(String projectType) {
        return projectRepository.findByProjectType(projectType).stream()
                .map(projectMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ProjectDTO> getProjectsByTechStack(List<String> techStack, boolean matchAll) {
        List<Project> projects = matchAll ?
                projectRepository.findByTechStackContainingAll(techStack) :
                projectRepository.findByTechStackContainingAny(techStack);

        return projects.stream()
                .map(projectMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ProjectDTO> getProjectsByStatus(String status) {
        return projectRepository.findByStatus(status).stream()
                .map(projectMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO updateProject(String id, ProjectDTO projectDTO) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        if (projectDTO.getProjectType() != null) {
            existingProject.setProjectType(projectDTO.getProjectType());
        }
        if (projectDTO.getTechStack() != null) {
            existingProject.setTechStack(projectDTO.getTechStack());
        }
        if (projectDTO.getRequirements() != null) {
            existingProject.setRequirements(projectDTO.getRequirements());
        }
        if (projectDTO.getResponsibilities() != null) {
            existingProject.setResponsibilities(projectDTO.getResponsibilities());
        }
        if (projectDTO.getTimelineWeeks() > 0) {
            existingProject.setTimelineWeeks(projectDTO.getTimelineWeeks());
        }
        if (projectDTO.getMeetingSchedule() != null) {
            existingProject.setMeetingSchedule(projectDTO.getMeetingSchedule());
        }
        if (projectDTO.getCommunicationChannel() != null) {
            existingProject.setCommunicationChannel(projectDTO.getCommunicationChannel());
        }
        if (projectDTO.getStatus() != null) {
            existingProject.setStatus(projectDTO.getStatus());
        }

        existingProject.setUpdatedAt(new Date());
        Project updatedProject = projectRepository.save(existingProject);

        notificationService.createSystemNotification(
                "Project Updated",
                "Your project has been successfully updated.",
                "Project",
                updatedProject.getId()
        );

        return projectMapper.convertToDTO(updatedProject);
    }

    public void deleteProject(String id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        projectRepository.delete(project);

        notificationService.createSystemNotification(
                "Project Deleted",
                "Your project has been successfully deleted.",
                "Project",
                id
        );
    }

    public ProjectDTO addContributor(String projectId, ContributorDTO contributorDTO) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));

        Contributor contributor;
        final ContributorDTO finalContributorDTO = contributorDTO;

        if (finalContributorDTO.getId() != null) {
            contributor = contributorRepository.findById(finalContributorDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Contributor not found with id: " + finalContributorDTO.getId()));
        } else {
            contributor = new Contributor();
            BeanUtils.copyProperties(finalContributorDTO, contributor);
            contributor = contributorRepository.save(contributor);
        }

        final Contributor finalContributor = contributor;

        boolean contributorExists = project.getContributors().stream()
                .anyMatch(c -> c.getId().equals(finalContributor.getId()));

        if (!contributorExists) {
            project.getContributors().add(finalContributor);
            project.setUpdatedAt(new Date());
            projectRepository.save(project);

            notificationService.createSystemNotification(
                    "Contributor Added",
                    "A new contributor has been added to your project.",
                    "Project",
                    projectId
            );
        }

        return projectMapper.convertToDTO(project);
    }

    public ProjectDTO removeContributor(String projectId, String contributorId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));

        boolean removed = project.getContributors().removeIf(c -> c.getId().equals(contributorId));

        if (removed) {
            project.setUpdatedAt(new Date());
            projectRepository.save(project);

            notificationService.createSystemNotification(
                    "Contributor Removed",
                    "A contributor has been removed from your project.",
                    "Project",
                    projectId
            );
        }

        return projectMapper.convertToDTO(project);
    }
}