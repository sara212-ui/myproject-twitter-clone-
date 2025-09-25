package com.example.backend.dto.mapper;

import com.example.backend.dto.LikeDto;
import com.example.backend.dto.PostDto;
import com.example.backend.dto.UserDto;
import com.example.backend.model.Like;
import com.example.backend.model.User;

import java.util.ArrayList;
import java.util.List;

public class LikeDtoMapper {
    public static LikeDto toLikeDto(Like like, User reqUser) {
        UserDto user=UserDtoMapper.toUserDto(like.getUser());
        UserDto reqUserDto=UserDtoMapper.toUserDto(reqUser);
        PostDto post=PostDtoMapper.toPostDto(like.getPost(),reqUser);
        LikeDto likeDto = new LikeDto();
        likeDto.setId(like.getId());
        likeDto.setPost(post);
        likeDto.setUser(user);
        return likeDto;
    }
    public static List<LikeDto> toLikeDto(List<Like> likes, User reqUser) {
        List<LikeDto> likeDtos = new ArrayList<>();
        for (Like like : likes) {
            UserDto user=UserDtoMapper.toUserDto(like.getUser());
            PostDto post=PostDtoMapper.toPostDto(like.getPost(),reqUser);
            LikeDto likeDto = new LikeDto();
            likeDto.setId(like.getId());
            likeDto.setPost(post);
            likeDto.setUser(user);
            likeDtos.add(likeDto);
        }
        return likeDtos;
    }
}
