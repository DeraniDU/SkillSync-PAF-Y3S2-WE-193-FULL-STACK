package com.example.post_creating.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;

@Data
@Document(collection = "files")

public class FileMetadata {




        @Id
        private String id;
        private String fileName;
        private String url;
        private String fileType; // image/jpeg, video/mp4
        private long size;
        private Duration videoDuration;
    }


