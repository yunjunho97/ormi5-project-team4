package com.example.ormi5projectteam4.controller.thymeleaf_controller;

import com.example.ormi5projectteam4.domain.dto.PagedPostsResponse;
import com.example.ormi5projectteam4.domain.dto.PostDTO;
import com.example.ormi5projectteam4.domain.dto.ProcessStatus;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "http://localhost:8080/post";

    @GetMapping("/home")
    public String home(@RequestParam(defaultValue = "0") int page, Model model) {
        String url = BASE_URL + "?page=" + page;
        ResponseEntity<PagedPostsResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PagedPostsResponse>() {}
        );

        PagedPostsResponse pagedPosts = response.getBody();
        model.addAttribute("posts", pagedPosts.getContent());
        model.addAttribute("currentPage", pagedPosts.getNumber());
        model.addAttribute("totalPages", pagedPosts.getTotalPages());
        model.addAttribute("totalElements", pagedPosts.getTotalElements());
        return "home";
    }

    @GetMapping("/proceed")
    public String proceed(@RequestParam(defaultValue = "0") int page, Model model) {
        String url = BASE_URL + "/proceed?page=" + page;
        ResponseEntity<PagedPostsResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PagedPostsResponse>() {}
        );

        PagedPostsResponse pagedPosts = response.getBody();
        model.addAttribute("posts", pagedPosts.getContent());
        model.addAttribute("currentPage", pagedPosts.getNumber());
        model.addAttribute("totalPages", pagedPosts.getTotalPages());
        model.addAttribute("totalElements", pagedPosts.getTotalElements());
        return "home";
    }

    @GetMapping("/home/location")
    public String location(@RequestParam(defaultValue = "0") int page, @RequestParam String foundLocation, Model model) {
        String url = BASE_URL + "/location?page=" + page + "&foundLocation=" + foundLocation;
        ResponseEntity<PagedPostsResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PagedPostsResponse>() {}
        );

        PagedPostsResponse pagedPosts = response.getBody();
        model.addAttribute("posts", pagedPosts.getContent());
        model.addAttribute("currentPage", pagedPosts.getNumber());
        model.addAttribute("totalPages", pagedPosts.getTotalPages());
        model.addAttribute("totalElements", pagedPosts.getTotalElements());
        return "home";
    }

    @GetMapping("/read-post/{id}")
    public String readPost(@PathVariable Long id, Model model) {
        String url = BASE_URL + "/" + id;
        ResponseEntity<PostDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                PostDTO.class);
        model.addAttribute("post", response.getBody());
        return "read-post";
    }

    @GetMapping("/write")
    public String writePost(Model model) {
        model.addAttribute("postDTO", new PostDTO());
        return "write-post";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute PostDTO postDTO,
                             @RequestParam("files") List<MultipartFile> files,
                             RedirectAttributes redirectAttributes) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("postDTO", postDTO);
            for(MultipartFile file : files) {
                body.add("files", new ByteArrayResource(file.getBytes()) {
                    @Override
                    public String getFilename() {
                        return file.getOriginalFilename();
                    }
                });
            }

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            ResponseEntity<PostDTO> response = restTemplate.exchange(
                    BASE_URL,
                    HttpMethod.POST,
                    requestEntity,
                    PostDTO.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                redirectAttributes.addFlashAttribute("message", "Post created successfully!");
                return "redirect:/home";
            } else {
                redirectAttributes.addFlashAttribute("error", "Failed to create post. Please try again.");
                return "redirect:/write";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An error occurred: " + e.getMessage());
            return "redirect:/write";
        }
    }

    //수정 요청
    @PostMapping("/update/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute ProcessStatus processStatus, RedirectAttributes redirectAttributes) {
        String url = BASE_URL + "/" + id;
        restTemplate.put(url, processStatus);
        redirectAttributes.addFlashAttribute("message", "Post updated successfully!");
        return "redirect:/read-post/" + id;
    }

    //삭제 요청
    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        String url = BASE_URL + "/" + id;
        restTemplate.delete(url);
        redirectAttributes.addFlashAttribute("message", "Post deleted successfully!");
        return "redirect:/home";
    }

}
