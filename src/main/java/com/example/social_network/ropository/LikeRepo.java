package com.example.social_network.ropository;

import com.example.social_network.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikeRepo extends JpaRepository<Likes,Long> {
    // đếm số like post
    @Query(nativeQuery = true,value = "select count(post_id) from likes where post_id =:idPost")
    Integer numberLikesPost (@Param(value = "idPost")Long idPost);

    // đếm số like comment
     @Query(nativeQuery = true,value = "select count(comment_id) from likes where post_id =:idComment")
    Integer numberLikesComment (@Param(value = "idComment")Long idComment);


}
