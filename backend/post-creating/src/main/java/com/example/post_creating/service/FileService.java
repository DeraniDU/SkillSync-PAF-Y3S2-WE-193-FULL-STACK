package com.example.post_creating.service;

import com.example.post_creating.exception.InvalidFileTypeException;
import com.example.post_creating.exception.VideoDurationExceededException;
import com.example.post_creating.model.FileMetadata;
import com.example.post_creating.repository.FileMetadataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.post_creating.dto.FileUploadDto;
import com.example.post_creating.utill.VideoUtil;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class FileService {
    private final AzureStorageService azureStorage;
    private final FileMetadataRepository metadataRepo;

    public FileMetadata uploadImage(FileUploadDto dto) throws IOException {
        validateImage(dto.getFile());
        String url = azureStorage.uploadFile(dto.getFile());
        return saveMetadata(dto.getFile(), url, null);
    }

    public FileMetadata uploadVideo(FileUploadDto dto) throws IOException {
        File tempFile = File.createTempFile("video", ".tmp");
        try {
            dto.getFile().transferTo(tempFile);
            validateVideo(tempFile);
            String url = azureStorage.uploadFile(dto.getFile());
            return saveMetadata(dto.getFile(), url, VideoUtil.getVideoDuration((MultipartFile) tempFile));
        } finally {
            tempFile.delete();
        }
    }

    private void validateImage(MultipartFile file) {
        if (!file.getContentType().startsWith("image/")) {
            throw new InvalidFileTypeException("Only images are allowed");
        }
    }

    private void validateVideo(File file) throws IOException {
        Duration duration = VideoUtil.getVideoDuration((MultipartFile) file);
        if (duration.toMinutes() > 3) {
            throw new VideoDurationExceededException("Video exceeds 3-minute limit");
        }
    }

    private FileMetadata saveMetadata(MultipartFile file, String url, Duration duration) {
        FileMetadata metadata = new FileMetadata();
        metadata.setFileName(file.getOriginalFilename());
        metadata.setUrl(url);
        metadata.setFileType(file.getContentType());
        metadata.setSize(file.getSize());
        metadata.setVideoDuration(duration);
        return metadataRepo.save(metadata);
    }
}
