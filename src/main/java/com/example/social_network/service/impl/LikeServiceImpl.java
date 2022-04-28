package com.example.social_network.service.impl;

import com.example.social_network.model.Likes;
import com.example.social_network.model.Users;
import com.example.social_network.ropository.CommentRepo;
import com.example.social_network.ropository.IUserRepo;
import com.example.social_network.ropository.LikeRepo;
import com.example.social_network.ropository.PostRepo;
import com.example.social_network.service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements ILikeService {

    @Autowired
    LikeRepo likeRepo;

    @Autowired
    IUserRepo userRepo;

    @Autowired
    PostRepo postRepo;

    @Autowired
    CommentRepo commentRepo;

    @Override
    public List<Users> listLikePost(Long idPost) {
        return userRepo.listUserLikePost(idPost);
    }

    @Override
    public List<Users> listLikeComment(Long idComment) {
        return userRepo.listUserLikeComment(idComment);
    }

    @Override
    public int countLikePost(Long idPost) {
        return likeRepo.numberLikesPost(idPost);
    }

    @Override
    public int countLikeComment(Long idComment) {
        return likeRepo.numberLikesComment(idComment);
    }

    @Override
    public void createLikePost(Long idUser,Long idPost) {
        Likes likes = new Likes(userRepo.findById(idUser).get(),postRepo.findById(idPost).get());
        likeRepo.save(likes);
    }

    @Override
    public void createLikeComment(Long idUser,Long idComment) {
        Likes likes = new Likes(userRepo.findById(idUser).get(),commentRepo.findById(idComment).get());
        likeRepo.save(likes);
    }

    @Override
    public void deleteLike(Long idLike) {
        likeRepo.deleteById(idLike);
    }


}
