package com.example.social_network.controller;

import com.example.social_network.model.Likes;
import com.example.social_network.model.Users;
import com.example.social_network.service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/Likes")
public class LikeController {
    @Autowired
    ILikeService likeService;

    // like bài post
    @GetMapping("/post/{idUser}/{idPost}")
    public ResponseEntity<Likes> likePost(@PathVariable Long idUser,@PathVariable Long idPost){
        likeService.createLikePost(idUser,idPost);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // like comment
    @GetMapping("/comment/{idUser}/{idComment}")
    public ResponseEntity<Likes> likeComment(@PathVariable Long idUser,@PathVariable Long idComment){
        likeService.createLikeComment(idUser,idComment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // xóa like post or comment
    @DeleteMapping("/delete/{idLike}")
    public ResponseEntity<Likes> deleteLike(@PathVariable Long idLike){
        likeService.deleteLike(idLike);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // xem số lượt like bài post
    @GetMapping("/countLikePost/{idPost}")
    public ResponseEntity<Integer> countLikePost(@PathVariable Long idPost){
        return new ResponseEntity<>(likeService.countLikePost(idPost),HttpStatus.OK);
    }

    // xem số lượt like comment
    @GetMapping("/countLikePost/{idComment}")
    public ResponseEntity<Integer> countLikeComment(@PathVariable Long idComment){
        likeService.countLikeComment(idComment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // xem danh sách lượt like bài post
    @GetMapping("/listLikePost/{idPost}")
    public ResponseEntity<List<Users>> listLikePost(@PathVariable Long idPost){
        return new ResponseEntity<>(likeService.listLikePost(idPost),HttpStatus.OK);
    }

    // xem danh sách lượt like comment
    @GetMapping("/listLikeComment/{idComment}")
    public ResponseEntity<List<Users>> listLikeComment(@PathVariable Long idComment){
        return new ResponseEntity<>(likeService.listLikeComment(idComment),HttpStatus.OK);
    }
}
