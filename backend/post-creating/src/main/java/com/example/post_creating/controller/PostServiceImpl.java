package com.example.post_creating.controller;

import com.example.post_creating.dto.PostDto;
import com.example.post_creating.model.Post;
import com.example.post_creating.repository.PostRepository;
import com.example.post_creating.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Post createPost(PostDto postDto) {
        Post post = new Post();
        mapDtoToEntity(postDto, post);
        return postRepository.save(post);
    }

    @Override
    public Post getPostById(String id) {
        return postRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post updatePost(String id, PostDto postDto) {
        Post post = getPostById(id);
        mapDtoToEntity(postDto, post);
        return postRepository.save(post);
    }

    @Override
    public void deletePost(String id) {
        postRepository.deleteById(id);
    }

    private void mapDtoToEntity(PostDto dto, Post entity) {
        entity.setCaption(dto.getCaption());
        entity.setPrerequisites(dto.getPrerequisites());
        entity.setTags(dto.getTags());
        entity.setSkills(dto.getSkills());
    }
}