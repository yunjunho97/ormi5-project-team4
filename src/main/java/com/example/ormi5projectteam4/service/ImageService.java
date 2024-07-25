package com.example.ormi5projectteam4.service;

import com.example.ormi5projectteam4.domain.Image;
import com.example.ormi5projectteam4.domain.ImageDTO;
import com.example.ormi5projectteam4.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image createImage(ImageDTO imageDTO){
        Image image = new Image();
        image.setImgUrl(imageDTO.getImgUrl());
        image.setCreatedAt(LocalDateTime.now());
        return imageRepository.save(image);
    }
}
