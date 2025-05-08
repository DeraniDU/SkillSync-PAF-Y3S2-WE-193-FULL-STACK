// src/main/java/com/example/post_creating/service/PostService.java
package com.example.post_creating.service;

import com.example.post_creating.dto.PostDto;
import com.example.post_creating.model.Post;

import java.util.List;

public interface PostService {
    Post createPost(PostDto postDto);
    Post getPostById(String id);
    List<Post> getAllPosts();
    Post updatePost(String id, PostDto postDto);
    void deletePost(String id);
}
