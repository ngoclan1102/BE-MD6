package com.example.social_network.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String link;

    @ManyToOne
    Users users;

    @ManyToOne
    Post post;
}
