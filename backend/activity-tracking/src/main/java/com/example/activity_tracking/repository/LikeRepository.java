package com.example.activity_tracking.repository;

import com.example.activity_tracking.model.Like;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserUserIdAndPostPostId(Long userId, Long postId);
    Page<Like> findByPostPostId(Long postId, Pageable pageable);
}