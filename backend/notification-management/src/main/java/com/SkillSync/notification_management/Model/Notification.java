package com.SkillSync.notification_management.Model;

import java.time.LocalDateTime;

public class Notification {
    private Long id;
    private String type; // "FRIEND_REQUEST", "LIKE", "COMMENT"
    private String message;
    private Long senderId;
    private String senderName;
    private Long receiverId;
    private boolean read;
    private LocalDateTime createdAt;

    // Default constructor
    public Notification() {
        this.createdAt = LocalDateTime.now();
        this.read = false;
    }

    // Constructor with fields
    public Notification(String type, String message, Long senderId, String senderName, Long receiverId) {
        this.type = type;
        this.message = message;
        this.senderId = senderId;
        this.senderName = senderName;
        this.receiverId = receiverId;
        this.createdAt = LocalDateTime.now();
        this.read = false;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}