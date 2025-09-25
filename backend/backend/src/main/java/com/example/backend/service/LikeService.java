package com.example.backend.service;

import com.example.backend.exception.PostException;
import com.example.backend.exception.UserException;
import com.example.backend.model.Like;
import com.example.backend.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LikeService{
    public Like likePost(Long postId, User user) throws UserException, PostException ;


    public List<Like> getAllLikes (Long postId) throws PostException;
}
