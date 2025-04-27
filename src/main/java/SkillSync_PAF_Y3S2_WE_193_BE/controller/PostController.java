package SkillSync_PAF_Y3S2_WE_193_BE.controller;

import SkillSync_PAF_Y3S2_WE_193_BE.dto.PostRequestDTO;
import SkillSync_PAF_Y3S2_WE_193_BE.dto.PostResponseDTO;
import SkillSync_PAF_Y3S2_WE_193_BE.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public PostResponseDTO createPost(@RequestBody PostRequestDTO postRequestDTO) {
        return postService.createPost(postRequestDTO);
    }

    @GetMapping
    public List<PostResponseDTO> getAllPosts() {
        return postService.getAllPosts();
    }
}
