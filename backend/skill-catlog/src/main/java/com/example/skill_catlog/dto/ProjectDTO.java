package com.example.skill_catlog.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectDTO {
    private String id;
    private String projectType;
    private List<String> techStack = new ArrayList<>();
    private List<String> requirements = new ArrayList<>();
    private List<String> responsibilities = new ArrayList<>();
    private int timelineWeeks;
    private String meetingSchedule;
    private String communicationChannel;
    private List<ContributorDTO> contributors = new ArrayList<>();
    private Date createdAt;
    private Date updatedAt;
    private String status;

    // Getters and Setters
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

    public List<ContributorDTO> getContributors() {
        return contributors;
    }

    public void setContributors(List<ContributorDTO> contributors) {
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