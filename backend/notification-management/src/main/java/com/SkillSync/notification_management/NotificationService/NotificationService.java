package com.SkillSync.notification_management.NotificationService;

import com.SkillSync.notification_management.Model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // In-memory storage for notifications (replace with database in production)
    private final Map<Long, List<Notification>> userNotifications = new ConcurrentHashMap<>();
    private Long notificationIdCounter = 1L;

    /**
     * Send a notification to a specific user
     */
    public void sendNotification(Notification notification) {
        // Set notification ID
        notification.setId(notificationIdCounter++);

        // Store notification
        Long receiverId = notification.getReceiverId();
        userNotifications.computeIfAbsent(receiverId, k -> new ArrayList<>()).add(notification);

        // Send notification to the specific user
        messagingTemplate.convertAndSendToUser(
                receiverId.toString(),
                "/topic/notifications",
                notification
        );
    }

    /**
     * Create and send a friend request notification
     */
    public Notification sendFriendRequestNotification(Long senderId, String senderName, Long receiverId) {
        Notification notification = new Notification(
                "FRIEND_REQUEST",
                senderName + " sent you a friend request",
                senderId,
                senderName,
                receiverId
        );
        sendNotification(notification);
        return notification;
    }

    /**
     * Create and send a like notification
     */
    public Notification sendLikeNotification(Long senderId, String senderName, Long receiverId, String contentType) {
        Notification notification = new Notification(
                "LIKE",
                senderName + " liked your " + contentType,
                senderId,
                senderName,
                receiverId
        );
        sendNotification(notification);
        return notification;
    }

    /**
     * Create and send a comment notification
     */
    public Notification sendCommentNotification(Long senderId, String senderName, Long receiverId, String contentType) {
        Notification notification = new Notification(
                "COMMENT",
                senderName + " commented on your " + contentType,
                senderId,
                senderName,
                receiverId
        );
        sendNotification(notification);
        return notification;
    }

    /**
     * Get all notifications for a user
     */
    public List<Notification> getUserNotifications(Long userId) {
        return userNotifications.getOrDefault(userId, new ArrayList<>());
    }

    /**
     * Get unread notifications for a user
     */
    public List<Notification> getUnreadNotifications(Long userId) {
        return userNotifications.getOrDefault(userId, new ArrayList<>())
                .stream()
                .filter(notification -> !notification.isRead())
                .collect(Collectors.toList());
    }

    /**
     * Mark a notification as read
     */
    public void markAsRead(Long userId, Long notificationId) {
        List<Notification> notifications = userNotifications.getOrDefault(userId, new ArrayList<>());
        for (Notification notification : notifications) {
            if (notification.getId().equals(notificationId)) {
                notification.setRead(true);
                break;
            }
        }
    }

    /**
     * Mark all notifications as read for a user
     */
    public void markAllAsRead(Long userId) {
        List<Notification> notifications = userNotifications.getOrDefault(userId, new ArrayList<>());
        for (Notification notification : notifications) {
            notification.setRead(true);
        }
    }
}