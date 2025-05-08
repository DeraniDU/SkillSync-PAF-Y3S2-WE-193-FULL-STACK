package com.example.post_creating.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class FileUploadDto {

        private MultipartFile file;
        private String postId; // Link to your Post entity
    }


