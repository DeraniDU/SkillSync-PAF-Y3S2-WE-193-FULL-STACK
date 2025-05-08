package com.example.skill_catlog.service;

import com.example.skill_catlog.dto.NotificationDTO;
import com.example.skill_catlog.exception.ResourceNotFoundException;
import com.example.skill_catlog.model.Notification;
import com.example.skill_catlog.repository.NotificationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public NotificationDTO createNotification(NotificationDTO notificationDTO) {
        Notification notification = convertToEntity(notificationDTO);
        notification.setCreatedAt(new Date());
        notification.setRead(false);

        Notification savedNotification = notificationRepository.save(notification);
        return convertToDTO(savedNotification);
    }

    public NotificationDTO createSystemNotification(String title, String message, String type, String relatedEntityId) {
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setType(type);
        notification.setRelatedEntityId(relatedEntityId);
        notification.setCreatedAt(new Date());
        notification.setRead(false);

        Notification savedNotification = notificationRepository.save(notification);
        return convertToDTO(savedNotification);
    }

    public NotificationDTO getNotificationById(String id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + id));
        return convertToDTO(notification);
    }

    public List<NotificationDTO> getNotificationsByUserId(String userId) {
        List<Notification> notifications = notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);
        return notifications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<NotificationDTO> getUnreadNotificationsByUserId(String userId) {
        List<Notification> notifications = notificationRepository.findByUserIdAndReadOrderByCreatedAtDesc(userId, false);
        return notifications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public long countUnreadNotifications(String userId) {
        return notificationRepository.countByUserIdAndRead(userId, false);
    }

    public NotificationDTO markNotificationAsRead(String id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + id));

        notification.setRead(true);
        Notification updatedNotification = notificationRepository.save(notification);
        return convertToDTO(updatedNotification);
    }

    public void markAllNotificationsAsRead(String userId) {
        List<Notification> unreadNotifications = notificationRepository.findByUserIdAndReadOrderByCreatedAtDesc(userId, false);

        for (Notification notification : unreadNotifications) {
            notification.setRead(true);
        }

        notificationRepository.saveAll(unreadNotifications);
    }

    public void deleteNotification(String id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + id));

        notificationRepository.delete(notification);
    }

    private Notification convertToEntity(NotificationDTO dto) {
        Notification notification = new Notification();
        BeanUtils.copyProperties(dto, notification);
        return notification;
    }

    private NotificationDTO convertToDTO(Notification notification) {
        NotificationDTO dto = new NotificationDTO();
        BeanUtils.copyProperties(notification, dto);
        return dto;
    }
}