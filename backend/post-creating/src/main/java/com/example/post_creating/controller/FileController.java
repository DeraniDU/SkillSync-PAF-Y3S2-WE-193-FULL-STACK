package com.example.post_creating.controller;

import com.example.post_creating.dto.FileUploadDto;
import com.example.post_creating.model.FileMetadata;
import com.example.post_creating.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    // Image upload endpoint
    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileMetadata> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("postId") String postId
    ) throws IOException {
        FileUploadDto dto = new FileUploadDto();
        dto.setFile(file);
        dto.setPostId(postId);
        return ResponseEntity.ok(fileService.uploadImage(dto));
    }

    // Video upload endpoint
    @PostMapping(value = "/video", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileMetadata> uploadVideo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("postId") String postId
    ) throws IOException {
        FileUploadDto dto = new FileUploadDto();
        dto.setFile(file);
        dto.setPostId(postId);
        return ResponseEntity.ok(fileService.uploadVideo(dto));
    }
}
