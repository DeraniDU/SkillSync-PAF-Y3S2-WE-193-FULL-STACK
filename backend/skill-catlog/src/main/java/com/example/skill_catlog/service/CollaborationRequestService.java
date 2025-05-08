package com.example.skill_catlog.service;

import com.example.skill_catlog.dto.CollaborationRequestDTO;
import com.example.skill_catlog.exception.ResourceNotFoundException;
import com.example.skill_catlog.model.CollaborationRequest;
import com.example.skill_catlog.repository.CollaborationRequestRepository;
import com.example.skill_catlog.repository.ProjectRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollaborationRequestService {

    @Autowired
    private CollaborationRequestRepository collaborationRequestRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private NotificationService notificationService;

    public CollaborationRequestDTO createCollaborationRequest(CollaborationRequestDTO collaborationRequestDTO) {
        // Verify that the project exists
        projectRepository.findById(collaborationRequestDTO.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + collaborationRequestDTO.getProjectId()));

        CollaborationRequest collaborationRequest = convertToEntity(collaborationRequestDTO);
        collaborationRequest.setCreatedAt(new Date());
        collaborationRequest.setStatus("Pending");

        CollaborationRequest savedCollaborationRequest = collaborationRequestRepository.save(collaborationRequest);

        // Create notification for collaboration request
        notificationService.createSystemNotification(
                "Collaboration Request Received",
                "A new collaboration request has been received for your project.",
                "Collaboration",
                savedCollaborationRequest.getId()
        );

        return convertToDTO(savedCollaborationRequest);
    }

    public CollaborationRequestDTO getCollaborationRequestById(String id) {
        CollaborationRequest collaborationRequest = collaborationRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Collaboration request not found with id: " + id));
        return convertToDTO(collaborationRequest);
    }

    public List<CollaborationRequestDTO> getCollaborationRequestsByProjectId(String projectId) {
        List<CollaborationRequest> collaborationRequests = collaborationRequestRepository.findByProjectId(projectId);
        return collaborationRequests.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<CollaborationRequestDTO> getCollaborationRequestsByProjectIdAndStatus(String projectId, String status) {
        List<CollaborationRequest> collaborationRequests = collaborationRequestRepository.findByProjectIdAndStatus(projectId, status);
        return collaborationRequests.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<CollaborationRequestDTO> getCollaborationRequestsByEmail(String email) {
        List<CollaborationRequest> collaborationRequests = collaborationRequestRepository.findByEmail(email);
        return collaborationRequests.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CollaborationRequestDTO updateCollaborationRequestStatus(String id, String status) {
        CollaborationRequest collaborationRequest = collaborationRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Collaboration request not found with id: " + id));

        collaborationRequest.setStatus(status);
        CollaborationRequest updatedCollaborationRequest = collaborationRequestRepository.save(collaborationRequest);

        // Create notification for status update
        String notificationTitle;
        String notificationMessage;

        if ("Approved".equals(status)) {
            notificationTitle = "Collaboration Request Approved";
            notificationMessage = "Your collaboration request has been approved.";
        } else if ("Rejected".equals(status)) {
            notificationTitle = "Collaboration Request Rejected";
            notificationMessage = "Your collaboration request has been rejected.";
        } else {
            notificationTitle = "Collaboration Request Status Updated";
            notificationMessage = "Your collaboration request status has been updated to " + status + ".";
        }

        notificationService.createSystemNotification(
                notificationTitle,
                notificationMessage,
                "Collaboration",
                updatedCollaborationRequest.getId()
        );

        return convertToDTO(updatedCollaborationRequest);
    }

    private CollaborationRequest convertToEntity(CollaborationRequestDTO dto) {
        CollaborationRequest collaborationRequest = new CollaborationRequest();
        BeanUtils.copyProperties(dto, collaborationRequest);
        return collaborationRequest;
    }

    private CollaborationRequestDTO convertToDTO(CollaborationRequest collaborationRequest) {
        CollaborationRequestDTO dto = new CollaborationRequestDTO();
        BeanUtils.copyProperties(collaborationRequest, dto);
        return dto;
    }
}