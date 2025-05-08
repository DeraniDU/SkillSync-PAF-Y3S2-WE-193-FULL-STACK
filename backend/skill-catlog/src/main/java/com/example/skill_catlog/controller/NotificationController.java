package com.example.skill_catlog.controller;

import com.example.skill_catlog.dto.NotificationDTO;
import com.example.skill_catlog.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Create a new notification
    @PostMapping
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody NotificationDTO notificationDTO) {
        NotificationDTO createdNotification = notificationService.createNotification(notificationDTO);
        return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
    }

    // Get notification by ID
    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> getNotificationById(@PathVariable String id) {
        NotificationDTO notification = notificationService.getNotificationById(id);
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }

    // Get all notifications for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByUserId(
            @PathVariable String userId,
            @RequestParam(required = false) Boolean unreadOnly) {

        List<NotificationDTO> notifications;
        if (unreadOnly != null && unreadOnly) {
            notifications = notificationService.getUnreadNotificationsByUserId(userId);
        } else {
            notifications = notificationService.getNotificationsByUserId(userId);
        }
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    // Get count of unread notifications for a user
    @GetMapping("/user/{userId}/unread-count")
    public ResponseEntity<Long> getUnreadNotificationCount(@PathVariable String userId) {
        long count = notificationService.countUnreadNotifications(userId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    // Mark notification as read
    @PatchMapping("/{id}/read")
    public ResponseEntity<NotificationDTO> markNotificationAsRead(@PathVariable String id) {
        NotificationDTO updatedNotification = notificationService.markNotificationAsRead(id);
        return new ResponseEntity<>(updatedNotification, HttpStatus.OK);
    }

    // Mark all notifications as read for a user
    @PatchMapping("/user/{userId}/read-all")
    public ResponseEntity<Void> markAllNotificationsAsRead(@PathVariable String userId) {
        notificationService.markAllNotificationsAsRead(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Delete notification
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable String id) {
        notificationService.deleteNotification(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get notifications by type
    @GetMapping("/type/{type}")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByType(
            @PathVariable String type,
            @RequestParam(required = false) String userId) {
        // Implementation would need to be added to service layer
        // Would filter by both type and userId if userId provided
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    // Get recent notifications (with pagination)
    @GetMapping("/recent")
    public ResponseEntity<List<NotificationDTO>> getRecentNotifications(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) String userId) {
        // Implementation would need to be added to service layer
        // Would return most recent notifications, optionally filtered by user
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}