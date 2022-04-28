package com.example.social_network.service.impl;

import com.example.social_network.dto.post_img.PostImgdto;
import com.example.social_network.model.Image;
import com.example.social_network.model.Post;
import com.example.social_network.ropository.PostRepo;
import com.example.social_network.security.userprincal.UserDetailService;
import com.example.social_network.service.IPostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    PostRepo postRepo;
    @Autowired
    UserDetailService userDetailService;

    @Autowired
    ImageServiceImpl imageServiceImpl;

    @Autowired
    CommentServiceImpl commentService;


    @Override
    public List<Post> findAll() {
        return postRepo.findAll();
    }

    @Override
    public Post save(Post post) {
        return postRepo.save(post);
    }

    @Override
    public void delete(Long id) {
        postRepo.deleteById(id);
    }

    @Override
    public PostImgdto findById(Long id) {
        Post post = postRepo.findById(id).get();
        PostImgdto postDto = new PostImgdto(post.getId(), post.getContent(),post.getStatus(), post.getDate_Post(), post.getCount_Like(), post.getUsers()
                , imageServiceImpl.findListImgByPostId(post.getId()));
//                    , commentService.findListCommentByIdPost(post.getId()));
       return postDto;
    }




//    @Override
//    public Post findById(Long id) {
//        return postRepo.findById(id).get();
//    }

    @Override
    public List<PostImgdto> findByTimePost() {

        List<Post> posts = postRepo.findPostToTime();
        List<PostImgdto> allPostDtos = new ArrayList<>();

        for (Post post : posts) {
            PostImgdto postDto = new PostImgdto(post.getId(), post.getContent(),post.getStatus(), post.getDate_Post(), post.getCount_Like(), post.getUsers()
                    , imageServiceImpl.findListImgByPostId(post.getId()));
//                    , commentService.findListCommentByIdPost(post.getId()));
            allPostDtos.add(postDto);
        }
        return allPostDtos;
    }

    @Override
    public Post findPostByPost_dto(Long id) {
        return postRepo.findById(id).get();
    }

//    public List<Post> findPostByUserId(Long id) {
//        List<Image> listImgByPostId = new ArrayList<>();
//        long idPost;
//        List<Post> listPostByUserId = postRepo.findPostByUserId(id);
//
//
//        for (int i = 0; i < listPostByUserId.size(); i++) {
//            idPost = listPostByUserId.get(i).getId();
//            listImgByPostId = imageServiceImpl.findListImgByPostId(idPost);
//            for (int j = 0; j < listImgByPostId.size(); j++) {
//                if (listImgByPostId.get(j).getPost().getId() == idPost) {
//                    listImgByPostId.add(listImgByPostId.get(j));
//                }
//            }
//        }
//        return null;
//    }

    public List<PostImgdto> findPostByUserCurrentId(Long id) {
        List<Post> posts = postRepo.findPostByUserId(id);
        List<PostImgdto> postDtos = new ArrayList<>();

        for (Post post : posts) {
            PostImgdto postDto = new PostImgdto(post.getId(), post.getContent(),post.getStatus(), post.getDate_Post(), post.getCount_Like(), post.getUsers(),imageServiceImpl.findListImgByPostId(post.getId()));
            postDtos.add(postDto);
        }
        return postDtos;
    }


}
