package com.example.social_network.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 0 : false : đang gửi lời mời kb
    // 1 : true : đã là bạn bè
    private boolean status;

    private LocalDateTime since;

    @ManyToOne
    Users
            // from :
            User1 ,
    // to :
    User2;

    public Friend(boolean status, LocalDateTime since, Users user1, Users user2) {
        this.status = status;
        this.since = since;
        User1 = user1;
        User2 = user2;
    }

    public Friend() {

    }
}
