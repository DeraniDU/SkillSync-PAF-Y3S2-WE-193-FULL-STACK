package SkillSync_PAF_Y3S2_WE_193_BE.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDto {

    private Long userId;
    private String username;
    private String email;
    private String profilePicture;
    private String bio;
    private LocalDateTime createdAt;
}