package com.example.backend.service;

import com.example.backend.exception.PostException;
import com.example.backend.exception.UserException;
import com.example.backend.model.Post;
import com.example.backend.model.User;
import com.example.backend.request.PostReplyReques;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    public Post createPost(Post req, User user) throws UserException;
    public List<Post> findAllPost();
    public Post repost(Long postId, User user) throws UserException, PostException;
    public Post findById(Long postId) throws PostException;
    public Post deletePostById(Long postId, Long userId) throws UserException,PostException;
//    public Post removeFromRepost(Long postId, User user)throws UserException, PostException;
    public Post createdReply(PostReplyReques req, User user) throws  PostException;
    public List<Post> getUserPost(User user) ;
    public List<Post>findByLikesContainsUser(User user);
}

