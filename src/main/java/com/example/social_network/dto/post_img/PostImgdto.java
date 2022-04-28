package com.example.social_network.dto.post_img;

import com.example.social_network.model.Image;
import com.example.social_network.model.Post;
import com.example.social_network.model.Users;
import lombok.Data;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;


@Data
public class PostImgdto {
    private Long id;
    private String content;
    private String status;
    private LocalDateTime date_Post;
    private int count_Like;

    Users users;

    List<Image> listImage;

    static public Post bulldPost(PostImgdto post){
        return new Post(post.getId(), post.getContent(), post.getStatus(),post.getDate_Post(),post.getCount_Like(),post.getUsers());
    }

    public PostImgdto(Long id, String content, String status, LocalDateTime date_Post, int count_Like, Users users, List<Image> listImage) {
        this.id = id;
        this.content = content;
        this.status = status;
        this.date_Post = date_Post;
        this.count_Like = count_Like;
        this.users = users;
        this.listImage = listImage;

    }
}
