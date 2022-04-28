package com.example.social_network.service;

import com.example.social_network.model.Image;

import java.util.List;

public interface IImageService {
    List<Image> listImg();
    void saveImg(Image image);
    void deleteImg(Long id);
    Image findImg(Long id);

    List<Image> findListImgByPostId(Long id);
}
