package com.example.backend.controller;

import com.example.backend.dto.PostDto;
import com.example.backend.dto.mapper.PostDtoMapper;
import com.example.backend.exception.PostException;
import com.example.backend.exception.UserException;
import com.example.backend.model.Post;
import com.example.backend.model.User;
import com.example.backend.request.PostReplyReques;
import com.example.backend.response.ApiResponse;
import com.example.backend.service.PostService;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(@RequestBody Post req
            , @RequestHeader("Authorization")String jwt) throws UserException, PostException {

        User user =userService.findUserProfileByJwt(jwt);
        Post post=postService.createPost(req, user);
        PostDto postDto= PostDtoMapper.toPostDto(post, user);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }
    @PostMapping("/reply")
    public ResponseEntity<PostDto> replyPost(@RequestBody PostReplyReques req
            , @RequestHeader("Authorization")String jwt) throws UserException, PostException{

        User user =userService.findUserProfileByJwt(jwt);
        Post post=postService.createdReply(req, user);
        PostDto postDto=PostDtoMapper.toPostDto(post, user);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }
    @PutMapping("/{postId}/repost")
    public ResponseEntity<PostDto> rePost(@PathVariable Long postId
            , @RequestHeader("Authorization")String jwt) throws UserException, PostException{

        User user =userService.findUserProfileByJwt(jwt);
        Post post=postService.repost(postId, user);
        PostDto postDto=PostDtoMapper.toPostDto(post, user);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }


    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> findPostById(@PathVariable Long postId
            , @RequestHeader("Authorization")String jwt) throws UserException, PostException{

        User user =userService.findUserProfileByJwt(jwt);
        Post post=postService.findById(postId);
        PostDto postDto=PostDtoMapper.toPostDto(post, user);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId
            , @RequestHeader("Authorization")String jwt) throws UserException, PostException{

        User user =userService.findUserProfileByJwt(jwt);
        Post post=postService.deletePostById(postId, user.getId());
        ApiResponse res=new  ApiResponse();
        res.setMessage("Post has been deleted successfully");
        res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> getAllposts(
            @RequestHeader("Authorization") String jwt) throws UserException, PostException {

        User user = userService.findUserProfileByJwt(jwt);
        List<Post> posts = postService.findAllPost();

        List<PostDto> postDtos = PostDtoMapper.toPostDtos(posts, user);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getUsersAllposts(@PathVariable Long userId,
                                                    @RequestHeader("Authorization")String jwt) throws UserException, PostException{

        User user =userService.findUserProfileByJwt(jwt);
        List <Post> posts=postService.getUserPost(user);

        List <PostDto> postDtos=PostDtoMapper.toPostDtos(posts, user);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }
    @GetMapping("/user/{userId}/likes")
    public ResponseEntity<PostDto> findPostByLikesConatainsUser(@PathVariable Long userId,
                                                                @RequestHeader("Authorization")String jwt) throws UserException, PostException{

        User user =userService.findUserProfileByJwt(jwt);
        List <Post> posts=postService.findByLikesContainsUser(user);

        List <PostDto> postDtos=PostDtoMapper.toPostDtos(posts, user);
        return new ResponseEntity<PostDto>((PostDto) postDtos, HttpStatus.OK);
    }


}