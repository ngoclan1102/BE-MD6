package com.example.social_network.service;

import com.example.social_network.dto.post_img.PostImgdto;
import com.example.social_network.model.Post;

import java.util.List;

public interface IPostService {
    List<Post> findAll();
    Post save(Post post);
    void delete(Long id);
    PostImgdto findById(Long id);
    List<PostImgdto> findByTimePost();
    Post findPostByPost_dto(Long id);

}
