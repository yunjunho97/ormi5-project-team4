package com.example.ormi5projectteam4.controller.rest_controller;

import com.example.ormi5projectteam4.domain.dto.MyPageDTO;
import com.example.ormi5projectteam4.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mypage")
public class MyPageController {
    private final MyPageService myPageService;

    @Autowired
    public MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    @GetMapping("/{userId}")
    public MyPageDTO getUserInfo(@PathVariable Long userId) {
        return myPageService.getUserInfo(userId);
    }

    @PutMapping("/{userId}")
    public void updateUserInfo(@PathVariable Long userId, @RequestBody MyPageDTO myPageDTO) {
        myPageService.updateUserInfo(userId, myPageDTO);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        myPageService.deleteUser(userId);
    }
}