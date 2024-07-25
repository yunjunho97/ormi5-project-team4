package com.example.ormi5projectteam4.service;

import com.example.ormi5projectteam4.domain.dto.ImageDTO;
import com.example.ormi5projectteam4.domain.entity.Animal;
import com.example.ormi5projectteam4.domain.entity.Image;
import com.example.ormi5projectteam4.domain.dto.PostDTO;
import com.example.ormi5projectteam4.domain.entity.Post;
import com.example.ormi5projectteam4.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final AnimalService animalService;
    private final ImageService imageService;

    @Autowired
    public PostService(PostRepository postRepository, AnimalService animalService, ImageService imageService) {
        this.postRepository = postRepository;
        this.animalService = animalService;
        this.imageService = imageService;
    }

    public List<PostDTO> getAllPost(){
        List<Post> all = postRepository.findAll();
        return postRepository.findAll().stream()
                .map(PostDTO::fromPost)
                .collect(Collectors.toList());
    }

    public Optional<PostDTO> getPostById(Integer postId) {
        return postRepository.findById(postId).map(PostDTO::fromPost);
    }

    @Transactional
    public PostDTO createPost(PostDTO postDTO) {
        Post post = convertToPost(postDTO);
        post.setCreatedAt(LocalDateTime.now());
        post = postRepository.save(post);

        for(ImageDTO imageDTO : postDTO.getImages()) {
            Image image = imageService.createImage(imageDTO);
            post.addImage(image);
        }
        return PostDTO.fromPost(post);
    }

    public Optional<PostDTO> updatePost(Integer postId, PostDTO postDTO) {
        return postRepository.findById(postId).map(o -> {
            o.setAdoptionStatus(postDTO.getAdoptionStatus());
            o.setUpdatedAt(LocalDateTime.now());
            return PostDTO.fromPost(postRepository.save(o));
        });
    }

    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException(""));
        post.getImages().clear();
        postRepository.save(post);
        postRepository.deleteById(postId);
    }

    private Post convertToPost(PostDTO postDTO) {
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setTitle(postDTO.getTitle());
        post.setFountAt(postDTO.getFountAt());
        post.setDetail(postDTO.getDetail());
        post.setContact(postDTO.getContact());
        post.setTempoLocation(postDTO.getTempoLocation());
        post.setCreatedAt(postDTO.getCreatedAt());
        post.setUpdatedAt(postDTO.getUpdatedAt());
        post.setAdoptionStatus(postDTO.getAdoptionStatus());
        post.setApproveStatus(postDTO.getApproveStatus());

        Animal animal = animalService.createAnimal(postDTO.getAnimalDTO());
        post.setAnimal(animal);

//        postDTO.getImages().stream()
//                .map(imageService::createImage)
//                .forEach(post::addImage);
        return post;
    }

    private Image convertToImage(ImageDTO imageDTO) {
        Image image = new Image();
        image.setId(imageDTO.getId());
        image.setImgUrl(imageDTO.getImgUrl());
        image.setCreatedAt(imageDTO.getCreatedAt());
        image.setUpdatedAt(imageDTO.getUpdatedAt());
        return image;
    }
}
