package com.example.social_network.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Data
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Users user;

    @ManyToOne
    private Comment comment;

    @ManyToOne
    private Post post;

    public Likes(Users user, Post post) {
        this.user = user;
        this.post = post;
    }

 public Likes(Users user, Comment comment) {
        this.user = user;
        this.comment = comment;
    }

    public Likes() {

    }

}
