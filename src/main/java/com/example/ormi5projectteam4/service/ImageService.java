package com.example.ormi5projectteam4.service;

import com.example.ormi5projectteam4.domain.entity.Image;
import com.example.ormi5projectteam4.domain.dto.ImageDTO;
import com.example.ormi5projectteam4.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private static final String UPLOAD_DIR = "static/animal_image/";

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

    public String uploadImage(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("파일을 선택해주세요.");
        }

        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.write(path, file.getBytes());

            String fileUrl = "/animal_image/" + fileName;
            Image image = new Image();
            image.setImgUrl(fileUrl);
            image.setCreatedAt(LocalDateTime.now());
//            image.setPost(null);
            imageRepository.save(image);

            return fileUrl;
        } catch (IOException e) {
            throw new RuntimeException("이미지 업로드 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    public String getImageUrl(Long id) {
        return imageRepository.findById(id)
                .map(Image::getImgUrl)
                .orElseThrow(() -> new RuntimeException("이미지를 찾을 수 없습니다."));
    }
}
