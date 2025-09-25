package com.example.backend.repository;

import com.example.backend.model.Post;
import com.example.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByIsPostTrueOrderByCreatedAtDesc();
    List<Post> findByRepostUserContainsOrUser_IdAndIsPostTrueOrderByCreatedAtDesc(User user, Long userId);
    List<Post> findByLikesContainingOrderByCreatedAtDesc(User user);

    @Query("Select p From Post p JOIN p.likes l where l.user.id=:userId")
    List<Post> findByLikesUser_id (Long userId);
}

