package com.example.ormi5projectteam4.controller.thymeleaf_controller;

import com.example.ormi5projectteam4.domain.constant.ApproveStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminPageController {
    @GetMapping("/manage")
    public String admin() {
        return "redirect:/manage/posts";
    }

    @GetMapping("/manage/posts")
    public String adminPostsWithPage(
            @RequestParam(required = false) ApproveStatus approveStatus,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size){
        return "admin-posts";
    }

    @GetMapping("/manage/member")
    public String adminMembers(){
        return "admin-members";
    }
}
