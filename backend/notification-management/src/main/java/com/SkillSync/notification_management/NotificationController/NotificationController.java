package com.SkillSync.notification_management.NotificationController;

import com.SkillSync.notification_management.Model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Call this method when a like or comment happens
    public Notification sendNotification(@DestinationVariable String userId, Notification notification) {
        // Process the notification and send it to the user-specific topic
        return notification;
    }
}
