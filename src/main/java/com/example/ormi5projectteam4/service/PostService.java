package com.example.ormi5projectteam4.service;

import com.example.ormi5projectteam4.domain.constant.AdoptionStatus;
import com.example.ormi5projectteam4.domain.constant.ApproveStatus;
import com.example.ormi5projectteam4.domain.dto.ImageDTO;
import com.example.ormi5projectteam4.domain.dto.ProcessStatus;
import com.example.ormi5projectteam4.domain.entity.Animal;
import com.example.ormi5projectteam4.domain.entity.Image;
import com.example.ormi5projectteam4.domain.dto.PostDTO;
import com.example.ormi5projectteam4.domain.entity.Post;
import com.example.ormi5projectteam4.domain.entity.User;
import com.example.ormi5projectteam4.repository.PostRepository;
import com.example.ormi5projectteam4.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AnimalService animalService;
    private final ImageService imageService;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository, AnimalService animalService, ImageService imageService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.animalService = animalService;
        this.imageService = imageService;
    }

//    public List<PostDTO> getAllPost(){
//        List<Post> all = postRepository.findAll();
//        return postRepository.findAll().stream()
//                .map(PostDTO::fromPost)
//                .collect(Collectors.toList());
//    }

    public Page<PostDTO> getAllPosts(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending());
        Page<Post> posts = postRepository.findAll(pageRequest);
        return posts.map(PostDTO::fromPost);
    }

    public Page<PostDTO> getPostsByFoundLocation(String foundLocation, Pageable pageable){
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending());
        Page<Post> posts = postRepository.findByFoundLocation(foundLocation, pageRequest);
        return posts.map(PostDTO::fromPost);
    }

    //    public Page<PostDTO> getPostsByApproveStatus(ApproveStatus approveStatus, Pageable pageable){
//        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending());
//        Page<Post> posts = postRepository.findByApproveStatus(approveStatus, pageRequest);
//        return posts.map(PostDTO::fromPost);
//    }

    public Optional<PostDTO> getPostById(Long postId) {
        return postRepository.findById(postId).map(PostDTO::fromPost);
    }

    @Transactional
    public PostDTO createPost(PostDTO postDTO, List<MultipartFile> files) {
        Post post = convertToPost(postDTO);
        post.setCreatedAt(LocalDateTime.now());
        post.setAdoptionStatus(AdoptionStatus.POSTING);
        post.setApproveStatus(ApproveStatus.PENDING);
        post = postRepository.save(post);

        for(MultipartFile file : files) {
            Image image = imageService.uploadImage(file);
            post.addImage(image);
        }


        //user
//        Long userId = postDTO.getUserInfoDTO().getId();
//        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
//        user.addPost(post);

        return PostDTO.fromPost(post);
    }

//    //dto 분리 고려
//    public Optional<PostDTO> updatePost(Integer postId, PostDTO postDTO) {
//        return postRepository.findById(postId).map(o -> {
////            o.setAdoptionStatus(postDTO.getAdoptionStatus());
//            o.setAdoptionStatus(AdoptionStatus.ADOPTED);
//            o.setUpdatedAt(LocalDateTime.now());
//            return PostDTO.fromPost(postRepository.save(o));
//        });
//    }

    public Optional<PostDTO> updatePost(Long postId, ProcessStatus processStatus) {
        return postRepository.findById(postId).map(o -> {
//            o.setAdoptionStatus(postDTO.getAdoptionStatus());
            o.setAdoptionStatus(AdoptionStatus.ADOPTED);
            o.setUpdatedAt(LocalDateTime.now());
            return PostDTO.fromPost(postRepository.save(o));
        });
    }

    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException(""));
        post.getImages().clear();

        //user
//        Long userId = post.getUser().getId();
//        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
//        user.removePost(post); //이 부분 후에 null exception 인가? test 해보기

        postRepository.save(post);
        postRepository.deleteById(postId);
    }

    private Post convertToPost(PostDTO postDTO) {
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setTitle(postDTO.getTitle());
        post.setFoundAt(postDTO.getFoundAt());
        post.setFoundLocation(postDTO.getFoundLocation());
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
