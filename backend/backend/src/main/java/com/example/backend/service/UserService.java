package com.example.backend.service;

import com.example.backend.exception.UserException;
import com.example.backend.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public User findUserById(Long userId) throws UserException;
    public User findUserProfileByJwt(String jwt) throws UserException;
    public User updateUser(Long userId, User user) throws UserException;
    public User followUser(Long userId, User user) throws UserException;
    public List<User> searchUser(String query) ;

    User orElseThrow(Object o);

    User orElse(Object o);
}

