package com.example.social_network.ropository;

import com.example.social_network.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImageRepo extends JpaRepository<Image, Long> {


    @Query(nativeQuery = true, value = "select * from image where image.post_id = :id")
    List<Image> findListImgByPostId(@Param("id") Long id);

}
