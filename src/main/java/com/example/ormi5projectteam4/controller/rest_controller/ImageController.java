package com.example.ormi5projectteam4.controller.rest_controller;

import com.example.ormi5projectteam4.domain.entity.Image;
import com.example.ormi5projectteam4.repository.ImageRepository;
import com.example.ormi5projectteam4.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        System.out.println("aaa");
        try {
            String fileUrl = imageService.uploadImage(file);
            return ResponseEntity.ok("이미지가 성공적으로 업로드되었습니다. URL: " + fileUrl);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getImageUrl(@PathVariable Long id) {
        try {
            String url = imageService.getImageUrl(id);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
