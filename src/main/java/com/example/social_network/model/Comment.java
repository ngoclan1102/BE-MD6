package com.example.social_network.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Date date_Comment;

    @ManyToOne
    Users users;

    @ManyToOne
    Post post;
}
