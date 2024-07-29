package com.example.ormi5projectteam4.controller.thymeleaf_controller;

import com.example.ormi5projectteam4.domain.dto.PagedPostsResponse;
import com.example.ormi5projectteam4.domain.dto.PostDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

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

    @GetMapping("/read-post/{id}")
    public String readPost(@PathVariable Integer id, Model model) {
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
    public String writePost() {
        return "write-post";
    }
}
