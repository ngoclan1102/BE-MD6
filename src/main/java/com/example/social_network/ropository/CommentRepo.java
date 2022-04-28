package com.example.social_network.ropository;

import com.example.social_network.model.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {

    @Query(nativeQuery = true, value = "select * from comment where post_id = :id")
    List<Comment> findListCommentByPostId(@Param("id") Long id);

}
