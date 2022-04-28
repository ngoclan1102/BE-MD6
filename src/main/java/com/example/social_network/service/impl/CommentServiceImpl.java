package com.example.social_network.service.impl;

import com.example.social_network.model.Comment;
import com.example.social_network.ropository.CommentRepo;
import com.example.social_network.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    CommentRepo commentRepo;

    @Override
    public List<Comment> findAll() {
        return commentRepo.findAll();
    }

    @Override
    public void save(Comment comment) {
        commentRepo.save(comment);
    }

    @Override
    public void delete(Long id) {
        commentRepo.deleteById(id);
    }

    @Override
    public Comment findById(Long id) {
        return commentRepo.findById(id).get();
    }

    @Override
    public List<Comment> findListCommentByIdPost(Long id) {
        return commentRepo.findListCommentByPostId(id);
    }
}
