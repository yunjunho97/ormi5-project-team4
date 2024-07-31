package com.example.ormi5projectteam4.controller.rest_controller;

import com.example.ormi5projectteam4.domain.constant.ApproveStatus;
import com.example.ormi5projectteam4.domain.constant.Role;
import com.example.ormi5projectteam4.domain.dto.UserManagementDto;
import com.example.ormi5projectteam4.domain.entity.Post;
import com.example.ormi5projectteam4.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/post")
    public ResponseEntity<Page<Post>> getPostsByApproveStatusAndPage(
            @RequestParam(required = false) ApproveStatus approveStatus,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {

        Page<Post> posts = adminService.getPostsByApproveStatus(approveStatus, page, size);

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/member/{page}")
    public ResponseEntity<List<UserManagementDto>> getAllUsers(@RequestParam(required = false) String email, @PathVariable int page) {

        // 1페이지당 17개의 게시글을 가져옴
        final int count = 17;
        int startIndex = (page - 1) * count;
        List<UserManagementDto> users;

        if (email == null) {
            users = adminService.getAllUsers().stream().skip(startIndex).limit(count).collect(Collectors.toList());
        } else {
            users = adminService.searchUserByEmail(email).stream().skip(startIndex).limit(count).collect(Collectors.toList());
        }

        return ResponseEntity.ok(users);
    }

    @PutMapping("/member/{id}")
    public ResponseEntity<UserManagementDto> changeUserRole(@PathVariable Long id, @RequestParam Role role) {
        UserManagementDto updateUser = adminService.changeUserRole(id, role);
        if (updateUser == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updateUser);
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<Post> changePostApproveStatus(@PathVariable Long id, @RequestParam ApproveStatus approveStatus){
        Post updatePost = adminService.changePostApproveStatus(id, approveStatus);
        if (updatePost == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatePost);
    }

//    @PutMapping("/notice/{id}")
//    public ResponseEntity<Notice> updateNotice(@PathVariable Integer id, @RequestBody NoticeDto noticeDto) {
//        Notice notice = adminService.getNoticeById(id);
//        if (notice == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        notice.setTitle(noticeDto.getTitle());
//        notice.setContent(noticeDto.getContent());
//        notice.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
//
//        Notice updatedNotice = adminService.saveNotice(notice);
//        return ResponseEntity.ok(updatedNotice);
//    }

//    @DeleteMapping("/notice/{id}")
//    public ResponseEntity<Void> deleteNotice(@PathVariable Integer id) {
//        Notice notice = adminService.getNoticeById(id);
//        if (notice == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        adminService.deleteNotice(id);
//        return ResponseEntity.noContent().build();
//    }
}
