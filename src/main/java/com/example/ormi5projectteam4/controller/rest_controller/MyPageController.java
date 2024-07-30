package com.example.ormi5projectteam4.controller.rest_controller;

import com.example.ormi5projectteam4.domain.dto.MyPageDTO;
import com.example.ormi5projectteam4.domain.dto.PostDTO;
import com.example.ormi5projectteam4.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{userId}/posts")
    public List<PostDTO> getPostsByUser(@PathVariable Long userId) {
        return myPageService.getPostsByUser(userId);
    }

    @GetMapping("/posts/{postId}")
    public String getPostDetail(@PathVariable Long postId, Model model) {
        // 게시글 상세 정보 조회
        PostDTO post = myPageService.getPostDetail(postId);

        if (post == null) {
            // 게시글이 존재하지 않을 경우 처리
            return "redirect:/error"; // 에러 페이지로 리다이렉트 혹은 예외 처리
        }

        // 모델에 게시글 정보 추가
        model.addAttribute("post", post);
        return "detail"; // detail.html 페이지로 포워딩
    }
}