// src/main/java/com/example/post_creating/repository/FileMetadataRepository.java
package com.example.post_creating.repository;

import com.example.post_creating.model.FileMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileMetadataRepository extends MongoRepository<FileMetadata, String> {
}
