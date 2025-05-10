package com.SkillSync.notification_management.NotificationController;

import com.SkillSync.notification_management.Model.Notification;
import com.SkillSync.notification_management.NotificationService.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*") // In production, restrict to your frontend domain
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * Endpoint to get all notifications for a user
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getUserNotifications(userId));
    }

    /**
     * Endpoint to get unread notifications for a user
     */
    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<Notification>> getUnreadNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getUnreadNotifications(userId));
    }

    /**
     * Endpoint to mark a notification as read
     */
    @PutMapping("/user/{userId}/read/{notificationId}")
    public ResponseEntity<?> markAsRead(@PathVariable Long userId, @PathVariable Long notificationId) {
        notificationService.markAsRead(userId, notificationId);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint to mark all notifications as read for a user
     */
    @PutMapping("/user/{userId}/read-all")
    public ResponseEntity<?> markAllAsRead(@PathVariable Long userId) {
        notificationService.markAllAsRead(userId);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint to send a friend request notification
     */
    @PostMapping("/friend-request")
    public ResponseEntity<Notification> sendFriendRequestNotification(@RequestBody Map<String, Object> request) {
        Long senderId = ((Number) request.get("senderId")).longValue();
        String senderName = (String) request.get("senderName");
        Long receiverId = ((Number) request.get("receiverId")).longValue();

        Notification notification = notificationService.sendFriendRequestNotification(senderId, senderName, receiverId);
        return ResponseEntity.ok(notification);
    }

    /**
     * Endpoint to send a like notification
     */
    @PostMapping("/like")
    public ResponseEntity<Notification> sendLikeNotification(@RequestBody Map<String, Object> request) {
        Long senderId = ((Number) request.get("senderId")).longValue();
        String senderName = (String) request.get("senderName");
        Long receiverId = ((Number) request.get("receiverId")).longValue();
        String contentType = (String) request.get("contentType"); // e.g., "post", "photo"

        Notification notification = notificationService.sendLikeNotification(senderId, senderName, receiverId, contentType);
        return ResponseEntity.ok(notification);
    }

    /**
     * Endpoint to send a comment notification
     */
    @PostMapping("/comment")
    public ResponseEntity<Notification> sendCommentNotification(@RequestBody Map<String, Object> request) {
        Long senderId = ((Number) request.get("senderId")).longValue();
        String senderName = (String) request.get("senderName");
        Long receiverId = ((Number) request.get("receiverId")).longValue();
        String contentType = (String) request.get("contentType"); // e.g., "post", "photo"

        Notification notification = notificationService.sendCommentNotification(senderId, senderName, receiverId, contentType);
        return ResponseEntity.ok(notification);
    }
}