// File: src/main/java/com/example/colab_request/model/CollabRequest.java
package com.example.colab_request.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;

@Document(collection = "collab_requests")
public class CollabRequest {
    @Id
    private String id;
    @NotBlank private String projectId;
    @NotBlank private String name;
    @NotBlank private String email;
    private String telephone;
    private String githubLink;
    @NotBlank private String whyCollaborate;
    private Date createdAt;
    private Date updatedAt;

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getGithubLink() { return githubLink; }
    public void setGithubLink(String githubLink) { this.githubLink = githubLink; }
    public String getWhyCollaborate() { return whyCollaborate; }
    public void setWhyCollaborate(String whyCollaborate) { this.whyCollaborate = whyCollaborate; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
}
