package com.example.ormi5projectteam4.controller.thymeleaf_controller;

import com.example.ormi5projectteam4.annotation.Secured;
import com.example.ormi5projectteam4.domain.constant.ApproveStatus;
import com.example.ormi5projectteam4.domain.constant.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminPageController {
    @Secured(role = Role.ADMIN)
    @GetMapping("/manage")
    public String admin() {
        return "redirect:/manage/posts";
    }

    @Secured(role = Role.ADMIN)
    @GetMapping("/manage/posts")
    public String adminPostsWithPage(
            @RequestParam(required = false) ApproveStatus approveStatus,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size){
        return "admin-posts";
    }

    @Secured(role = Role.ADMIN)
    @GetMapping("/manage/member")
    public String adminMembers(){
        return "admin-members";
    }
}
