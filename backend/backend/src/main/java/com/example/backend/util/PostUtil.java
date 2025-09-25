package com.example.backend.util;

import com.example.backend.model.Like;
import com.example.backend.model.Post;
import com.example.backend.model.User;

public class PostUtil {
    public final static boolean isLikedByReqUser(User reqUser, Post post){
        for(Like like: post.getLikes()){
            if(like.getUser().getId()==(reqUser.getId())){
                return true;
            }
        }
        return false;
    }
    public final static boolean isRepostedByReqUser(User reqUser, Post post){
        for(User user: post.getRepostUser()){
            if(user.getId()== (reqUser.getId())){
                return true;
            }
        }
        return false;
    }
}