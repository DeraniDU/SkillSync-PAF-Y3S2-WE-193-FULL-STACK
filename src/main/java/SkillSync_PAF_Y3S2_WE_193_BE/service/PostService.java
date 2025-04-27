package SkillSync_PAF_Y3S2_WE_193_BE.service;

import SkillSync_PAF_Y3S2_WE_193_BE.dto.PostRequestDTO;
import SkillSync_PAF_Y3S2_WE_193_BE.dto.PostResponseDTO;

import java.util.List;

public interface PostService {
    PostResponseDTO createPost(PostRequestDTO postRequestDTO);
    List<PostResponseDTO> getAllPosts();
}
