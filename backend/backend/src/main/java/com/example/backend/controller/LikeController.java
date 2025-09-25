package com.example.backend.controller;

import com.example.backend.dto.LikeDto;
import com.example.backend.dto.mapper.LikeDtoMapper;
import com.example.backend.exception.PostException;
import com.example.backend.exception.UserException;
import com.example.backend.model.Like;
import com.example.backend.model.User;
import com.example.backend.service.LikeService;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LikeController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private UserService userService;
    @PostMapping("/{postId}/like")
    public ResponseEntity<LikeDto> likePost(@PathVariable Long postId
            , @RequestHeader("Authorization")String jwt) throws UserException, PostException
    {

        User user =userService.findUserProfileByJwt(jwt);
        Like like =likeService.likePost(postId, user);
        LikeDto  likeDto= LikeDtoMapper.toLikeDto(like,user);



        return new ResponseEntity<LikeDto>(likeDto, HttpStatus.CREATED);

    }
    @PostMapping("/post{postId}/")
    public ResponseEntity<LikeDto>getAllLikes(@PathVariable Long postId
            , @RequestHeader("Authorization")String jwt) throws UserException, PostException
    {
        User user =userService.findUserProfileByJwt(jwt);
        List<Like> likes=likeService.getAllLikes(postId);
        List<LikeDto> likeDtos= LikeDtoMapper.toLikeDto(likes,user);

        return new ResponseEntity<LikeDto >((LikeDto) likeDtos, HttpStatus.OK);

    }
}
