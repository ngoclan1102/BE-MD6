package com.example.social_network.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime since;

    private String content;

    @ManyToOne
    private Users from;

    @ManyToOne
    private Users to;

    public Notification(LocalDateTime since, String content, Users from, Users to) {
        this.since = since;
        this.content = content;
        this.from = from;
        this.to = to;
    }

    public Notification() {

    }
}
