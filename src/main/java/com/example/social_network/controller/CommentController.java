package com.example.social_network.controller;

import com.example.social_network.model.Comment;
import com.example.social_network.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/Comments")
public class CommentController {
    @Autowired
    ICommentService commentService;

    @GetMapping
    public ResponseEntity<List<Comment>> products() {
        return new ResponseEntity<>(commentService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Comment> create(@RequestBody Comment comment) {
        commentService.save(comment);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> findById(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.findById(id) , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        commentService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> edit(@PathVariable Long id, @RequestBody Comment comment) {
        comment.setId(id);
        commentService.save(comment);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
}
