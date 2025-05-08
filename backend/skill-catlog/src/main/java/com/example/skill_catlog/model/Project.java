package com.example.skill_catlog.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "projects")
public class Project {
    @Id
    private String id;

    @NotBlank(message = "Project type is required")
    private String projectType;

    @NotNull(message = "Tech stack is required")
    private List<String> techStack = new ArrayList<>();

    @NotNull(message = "Requirements are required")
    private List<String> requirements = new ArrayList<>();

    @NotNull(message = "Responsibilities are required")
    private List<String> responsibilities = new ArrayList<>();

    @NotNull(message = "Timeline is required")
    private int timelineWeeks;

    @NotBlank(message = "Meeting schedule is required")
    private String meetingSchedule;

    @NotBlank(message = "Communication channel is required")
    private String communicationChannel;

    private List<Contributor> contributors = new ArrayList<>();

    private Date createdAt;
    private Date updatedAt;
    private String status;

    // Getters and Setters (same as before)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public List<String> getTechStack() {
        return techStack;
    }

    public void setTechStack(List<String> techStack) {
        this.techStack = techStack;
    }

    public List<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }

    public List<String> getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(List<String> responsibilities) {
        this.responsibilities = responsibilities;
    }

    public int getTimelineWeeks() {
        return timelineWeeks;
    }

    public void setTimelineWeeks(int timelineWeeks) {
        this.timelineWeeks = timelineWeeks;
    }

    public String getMeetingSchedule() {
        return meetingSchedule;
    }

    public void setMeetingSchedule(String meetingSchedule) {
        this.meetingSchedule = meetingSchedule;
    }

    public String getCommunicationChannel() {
        return communicationChannel;
    }

    public void setCommunicationChannel(String communicationChannel) {
        this.communicationChannel = communicationChannel;
    }

    public List<Contributor> getContributors() {
        return contributors;
    }

    public void setContributors(List<Contributor> contributors) {
        this.contributors = contributors;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}