package com.example.backend.util;

import com.example.backend.model.User;

public class UserUtil{
    public static final boolean isReqUser(User reqUser, User user2){

        return
                reqUser.getId()==(user2.getId());}
    public static final boolean isFollowedByReqUser(User reqUser, User user2) {
        return
                reqUser.getFollowing().contains(user2);
    }
}