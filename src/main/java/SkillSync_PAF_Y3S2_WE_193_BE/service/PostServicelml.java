package SkillSync_PAF_Y3S2_WE_193_BE.service;

import SkillSync_PAF_Y3S2_WE_193_BE.dto.PostRequestDTO;
import SkillSync_PAF_Y3S2_WE_193_BE.dto.PostResponseDTO;
import SkillSync_PAF_Y3S2_WE_193_BE.model.Post;
import SkillSync_PAF_Y3S2_WE_193_BE.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServicelml implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostResponseDTO createPost(PostRequestDTO postRequestDTO) {
        Post post = new Post();
        post.setTitle(postRequestDTO.getTitle());
        post.setContent(postRequestDTO.getContent());
        Post savedPost = postRepository.save(post);
        return new PostResponseDTO(savedPost.getId(), savedPost.getTitle(), savedPost.getContent());
    }

    @Override
    public List<PostResponseDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(post -> new PostResponseDTO(post.getId(), post.getTitle(), post.getContent()))
                .collect(Collectors.toList());
    }
}
