package com.example.ormi5projectteam4.controller.thymeleaf_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminPageController {
    @GetMapping("/manage")
    public String admin() {
        return "redirect:/manage/posts/1";
    }

    @GetMapping("/manage/posts")
    public String adminPosts(){
        return "redirect:/manage/posts/1";
    }

    @GetMapping("/manage/posts/{id}")
    public String adminPostsWithPage(@PathVariable Long id){
        return "admin-posts";
    }

    @GetMapping("/manage/member")
    public String adminMembers(){
        return "admin-members";
    }
}
