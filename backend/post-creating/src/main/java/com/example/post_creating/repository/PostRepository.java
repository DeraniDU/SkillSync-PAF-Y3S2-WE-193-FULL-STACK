// src/main/java/com/example/post_creating/repository/PostRepository.java
package com.example.post_creating.repository;

import com.example.post_creating.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
