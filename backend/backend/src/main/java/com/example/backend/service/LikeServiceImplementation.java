package com.example.backend.service;

import com.example.backend.exception.PostException;
import com.example.backend.exception.UserException;
import com.example.backend.model.Like;
import com.example.backend.model.Post;
import com.example.backend.model.User;
import com.example.backend.repository.LikeRepository;
import com.example.backend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImplementation implements LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    @Override
    public Like likePost(Long postId, User user) throws UserException, PostException {
        Like existingLike = likeRepository.isLikeExist(user.getId(), postId);

        if (existingLike != null) {
            likeRepository.delete(existingLike);
            return null; // explicitly tell frontend it's now unliked
        }

        Post post = postService.findById(postId);

        Like newLike = new Like();
        newLike.setPost(post);
        newLike.setUser(user);

        return likeRepository.save(newLike); // no manual post.getLikes().add()
    }


    @Override
    public List<Like> getAllLikes(Long postId) throws PostException {
        Post post=postService.findById(postId);
        List<Like> likes=likeRepository.findByPostId(postId);
        return likes;
    }
}