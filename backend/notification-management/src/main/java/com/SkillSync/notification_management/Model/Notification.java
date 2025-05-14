package com.SkillSync.notification_management.Model;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Notification {
    private String message;
    private String recipientUserId; // user to notify
    private String senderUserId;    // user who liked or commented
    private String type;            // "like" or "comment"
    private Long postId;
    // getters and setters
}
