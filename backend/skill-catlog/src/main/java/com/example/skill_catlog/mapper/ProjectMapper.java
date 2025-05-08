package com.example.skill_catlog.mapper;

import com.example.skill_catlog.dto.ContributorDTO;
import com.example.skill_catlog.dto.ProjectDTO;
import com.example.skill_catlog.model.Contributor;
import com.example.skill_catlog.model.Project;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {

    public Project convertToEntity(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setId(projectDTO.getId());
        project.setProjectType(projectDTO.getProjectType());
        project.setTechStack(projectDTO.getTechStack());
        project.setRequirements(projectDTO.getRequirements());
        project.setResponsibilities(projectDTO.getResponsibilities());
        project.setTimelineWeeks(projectDTO.getTimelineWeeks());
        project.setMeetingSchedule(projectDTO.getMeetingSchedule());
        project.setCommunicationChannel(projectDTO.getCommunicationChannel());
        project.setStatus(projectDTO.getStatus());

        // Map contributor DTOs to entities
        if (projectDTO.getContributors() != null) {
            List<Contributor> contributors = projectDTO.getContributors().stream()
                    .map(this::convertContributorDTOToEntity)
                    .collect(Collectors.toList());
            project.setContributors(contributors);
        } else {
            project.setContributors(new ArrayList<>());
        }

        return project;
    }

    public ProjectDTO convertToDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setProjectType(project.getProjectType());
        projectDTO.setTechStack(project.getTechStack());
        projectDTO.setRequirements(project.getRequirements());
        projectDTO.setResponsibilities(project.getResponsibilities());
        projectDTO.setTimelineWeeks(project.getTimelineWeeks());
        projectDTO.setMeetingSchedule(project.getMeetingSchedule());
        projectDTO.setCommunicationChannel(project.getCommunicationChannel());
        projectDTO.setStatus(project.getStatus());
        projectDTO.setCreatedAt(project.getCreatedAt());
        projectDTO.setUpdatedAt(project.getUpdatedAt());

        // Map contributor entities to DTOs
        if (project.getContributors() != null) {
            List<ContributorDTO> contributorDTOs = project.getContributors().stream()
                    .map(this::convertContributorToDTO)
                    .collect(Collectors.toList());
            projectDTO.setContributors(contributorDTOs);
        } else {
            projectDTO.setContributors(new ArrayList<>());
        }

        return projectDTO;
    }

    private Contributor convertContributorDTOToEntity(ContributorDTO contributorDTO) {
        Contributor contributor = new Contributor();
        BeanUtils.copyProperties(contributorDTO, contributor);
        return contributor;
    }

    private ContributorDTO convertContributorToDTO(Contributor contributor) {
        ContributorDTO contributorDTO = new ContributorDTO();
        BeanUtils.copyProperties(contributor, contributorDTO);
        return contributorDTO;
    }
}