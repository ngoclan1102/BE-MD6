package com.example.social_network.controller;

import com.example.social_network.dto.post_img.PostImgdto;
import com.example.social_network.dto.respon.ResponMess;
import com.example.social_network.model.CheckDate;
import com.example.social_network.model.Image;
import com.example.social_network.model.Post;
import com.example.social_network.model.Users;
import com.example.social_network.security.userprincal.UserDetailService;
import com.example.social_network.service.IImageService;
import com.example.social_network.service.impl.IUserServiceImpl;
import com.example.social_network.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("post")
public class PostController {
    @Autowired
    PostServiceImpl postService;

    @Autowired
    UserDetailService userDetailService;

    @Autowired
    IImageService imageService;

    @Autowired
    IUserServiceImpl iUserService;


//    cần hỏi lại cách xử lý thời gian trong sql;



//    show list thì yêu cầu sắp xếp bài theo thời gian! (phục vụ trang home), trang tường nhà thiết kế sau( ý đồ xét lại list theo id user)
//    phân trang theo pagination scroll
    @GetMapping
    public ResponseEntity<List<PostImgdto>> findAllPost() {
        List<PostImgdto> postList = postService.findByTimePost();
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

   @GetMapping("/findAllByUserId/{idUser}")
   public ResponseEntity <List<PostImgdto>> findPostByUserId(@PathVariable Long idUser) {
       List<PostImgdto> postListByUserId =  postService.findPostByUserCurrentId(idUser);
       return new ResponseEntity<>(postListByUserId, HttpStatus.OK);
   }



    @PostMapping
    public ResponseEntity<?> create(@RequestBody PostImgdto post) {

        Users users =iUserService.findUserById(post.getUsers().getId())  ;
        post.setDate_Post(CheckDate.getTimePost());
        post.setUsers(users);
        Post postNew = PostImgdto.bulldPost(post);
        postService.save(postNew);

        for (Image img: post.getListImage()) {
            img.setUsers(post.getUsers());
            img.setPost(postNew);
            imageService.saveImg(img);
        }

        return new ResponseEntity<>(post, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<PostImgdto> findById(@PathVariable Long id) {
        return new ResponseEntity<PostImgdto>(postService.findById(id) , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        postService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PostImgdto> edit(@RequestBody PostImgdto post_dto) {
        Post post = postService.findPostByPost_dto(post_dto.getId());
        post.setContent(post_dto.getContent());
        post.setCount_Like(post_dto.getCount_Like());
        CheckDate checkDate = new CheckDate();
        post.setDate_Post(checkDate.getTimePost());
        post.setUsers(post_dto.getUsers());

//        List<Image> listimageEdit = new ArrayList<>();
//         for (int i = 0; i < postService.findAll().size(); i++) {
//            if (postService.findAll().get(i).getId() == post_dto.getId()) {
//                for (Image img : post_dto.getListImage()) {
//                    img.setUsers(post_dto.getUsers());
//                    imageService.saveImg(img);
//                }
//            }
//        }
        List<Image> imageListEdit = imageService.findListImgByPostId(post_dto.getId());


//        post_dto.getListImage();

        postService.save(post);

        return new ResponseEntity<PostImgdto>(HttpStatus.OK);
    }

}
