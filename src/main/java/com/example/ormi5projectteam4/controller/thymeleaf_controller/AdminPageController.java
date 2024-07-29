package com.example.ormi5projectteam4.controller.thymeleaf_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {
    @GetMapping("/admin")
    public String admin() {
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts")
    public String posts(){
        return "admin-posts";
    }
}
