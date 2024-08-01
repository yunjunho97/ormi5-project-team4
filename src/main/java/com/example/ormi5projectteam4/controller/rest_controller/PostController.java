package com.example.ormi5projectteam4.controller.rest_controller;

import com.example.ormi5projectteam4.domain.dto.PostDTO;
import com.example.ormi5projectteam4.domain.dto.ProcessStatus;
import com.example.ormi5projectteam4.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

//    @GetMapping
//    public ResponseEntity<List<PostDTO>> getAllPosts() {
//        List<PostDTO> allPost = postService.getAllPost();
//        return ResponseEntity.ok(allPost);
//    }

    @GetMapping
    public ResponseEntity<Page<PostDTO>> getAllPosts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<PostDTO> posts = postService.getAllPosts(pageRequest);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/location")
    public ResponseEntity<Page<PostDTO>> getPostsByFoundAt(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12") int size, @RequestParam String foundLocation){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<PostDTO> postsByFoundLocation = postService.getPostsByFoundLocation(foundLocation, pageRequest);
        return ResponseEntity.ok(postsByFoundLocation);
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(
            @RequestPart PostDTO postDTO, @RequestPart List<MultipartFile> files) {
        return ResponseEntity.ok(postService.createPost(postDTO, files));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<PostDTO> updatePost(@PathVariable Integer id, @RequestBody PostDTO postDTO) {
//        return postService.updatePost(id, postDTO)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @RequestBody ProcessStatus processStatus) {
        return postService.updatePost(id, processStatus)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
