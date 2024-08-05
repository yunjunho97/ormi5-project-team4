package com.example.ormi5projectteam4.controller.rest_controller;

import com.example.ormi5projectteam4.domain.constant.AdoptionStatus;
import com.example.ormi5projectteam4.domain.dto.PostDTO;
import com.example.ormi5projectteam4.domain.dto.ProcessStatus;
import com.example.ormi5projectteam4.domain.dto.UserDto;
import com.example.ormi5projectteam4.service.AuthenticationService;
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
    private final AuthenticationService authenticationService;

    @Autowired
    public PostController(PostService postService, AuthenticationService authenticationService) {
        this.postService = postService;
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public ResponseEntity<Page<PostDTO>> getAllPosts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<PostDTO> posts = postService.getAllPosts(pageRequest); //나중에 승인된 게시물만으로 변경
        return ResponseEntity.ok(posts);
    }

    //+approve
    @GetMapping("/proceed")
    public ResponseEntity<Page<PostDTO>> getProceedPosts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12") int size, @RequestParam AdoptionStatus adoptionstatus) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<PostDTO> posts = postService.getPostsByAdoptionStatus(adoptionstatus, pageRequest);
        return ResponseEntity.ok(posts);
    }

    //+approve +proceed
    @GetMapping("/location")
    public ResponseEntity<Page<PostDTO>> getPostsByFoundAt(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12") int size, @RequestParam String foundLocation){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<PostDTO> postsByFoundLocation = postService.getPostsByFoundLocation(foundLocation, pageRequest);
        return ResponseEntity.ok(postsByFoundLocation);
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(
            @RequestPart PostDTO postDTO, @RequestPart List<MultipartFile> files) {
        UserDto userDto = authenticationService.getUserDto()
                .orElseThrow(() -> new RuntimeException("User not found"));
        postDTO.setUserId(userDto.getId());
        return ResponseEntity.ok(postService.createPost(postDTO, files));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @RequestBody ProcessStatus processStatus) {
        UserDto userDto = authenticationService.getUserDto()
                .orElseThrow(() -> new RuntimeException("User not found"));
        return postService.updatePost(id, userDto, processStatus)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        UserDto userDto = authenticationService.getUserDto()
                .orElseThrow(() -> new RuntimeException("User not found"));
        postService.deletePost(id, userDto);
        return ResponseEntity.noContent().build();
    }
}
