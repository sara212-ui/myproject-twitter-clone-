package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Post {

    //post and reply(COMMENT) both

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne
    private User user;

    private String content;

    private String image;

    private String video;

    @OneToMany(mappedBy = "post", cascade=CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    @OneToMany
    private List<Post> replyPosts= new ArrayList<>();

    @ManyToMany
    private List<User> repostUser= new ArrayList<>();


    @ManyToOne
    private Post replyFor;

    private boolean isReply;
    private boolean isPost;

    private LocalDateTime createdAt;

}
