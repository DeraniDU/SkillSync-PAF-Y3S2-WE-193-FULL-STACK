package com.example.activity_tracking.repository;

import com.example.activity_tracking.model.SkillPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillPostRepository extends JpaRepository<SkillPost, Long> {
    Page<SkillPost> findByUserUserId(Long userId, Pageable pageable);
}