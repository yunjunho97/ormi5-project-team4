package com.example.ormi5projectteam4.controller.rest_controller;

import com.example.ormi5projectteam4.domain.dto.MyPageDTO;
import com.example.ormi5projectteam4.domain.dto.PostDTO;
import com.example.ormi5projectteam4.service.MyPageService;
import jakarta.servlet.http.HttpSession;
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


    @GetMapping("/this/info/{userId}")
    public MyPageDTO getUserInfo(@PathVariable Long userId) {
        return myPageService.getUserInfo(userId);
    }

    @PutMapping("/this/{userId}")
    public void updateUserInfo(@PathVariable Long userId, @RequestBody MyPageDTO myPageDTO) {
        myPageService.updateUserInfo(userId, myPageDTO);
    }

    @DeleteMapping("/this/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        myPageService.deleteUser(userId);
    }

    @GetMapping("/this/post/{userId}")
    public List<PostDTO> getPostsByUser(@PathVariable Long userId) {
        return myPageService.getPostsByUser(userId);
    }

    @GetMapping("/read-post/{postId}")
   public String getPostDetail(@PathVariable Integer postId, Model model, HttpSession session) {
        // 세션에서 현재 사용자의 ID를 가져옵니다
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {

            return "redirect:/login";
        }


        PostDTO post = myPageService.getMyPostDetail(postId, userId);

        if (post == null) {
            return "redirect:/error";
        }

        // 모델에 게시물 정보를 추가합니다
        model.addAttribute("post", post);
        return "detail"; // detail.html 페이지로 포워딩합니다
    }
}