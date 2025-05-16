package com.example.activity_tracking.repository;

import com.example.activity_tracking.model.Media;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    Optional<Media> findByPostPostIdAndUrl(Long postId, String url);
    Page<Media> findByPostPostId(Long postId, Pageable pageable);
    long countByPostPostId(Long postId);
}