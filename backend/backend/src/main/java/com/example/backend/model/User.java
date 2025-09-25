package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_entity_generator")
//    @SequenceGenerator(name = "my_entity_generator", sequenceName = "my_entity_seq", allocationSize = 1)
//    private Long id;

    private String fullName;
    private String location;
    private String website;
    private String birthDate;
    private String email;
    private String password;
    private String mobile;
    private String image;
    private String backgroundImage;
    private String bio;
    private String req_user;
    private boolean login_with_google;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> post = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    private List<User> followers  = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    private List<User> following  = new ArrayList<>();




}

