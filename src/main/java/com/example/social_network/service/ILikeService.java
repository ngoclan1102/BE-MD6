package com.example.social_network.service;

import com.example.social_network.model.Users;

import java.util.List;

public interface ILikeService {
    List<Users> listLikePost(Long idPost);
    List<Users> listLikeComment(Long idComment);
    int countLikePost(Long idPost);
    int countLikeComment(Long Comment);
    void createLikePost(Long idUser,Long idPost);
    void createLikeComment(Long idUser,Long idComment);
    void deleteLike(Long idLike);
}
