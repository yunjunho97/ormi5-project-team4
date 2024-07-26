package com.example.ormi5projectteam4.controller.rest_controller;

import com.example.ormi5projectteam4.domain.constant.ApproveStatus;
import com.example.ormi5projectteam4.domain.entity.Notice;
import com.example.ormi5projectteam4.domain.entity.Post;
import com.example.ormi5projectteam4.domain.entity.User;
import com.example.ormi5projectteam4.domain.dto.NoticeDto;
import com.example.ormi5projectteam4.domain.dto.UserRoleDto;
import com.example.ormi5projectteam4.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/post")
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> posts = adminService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/post")
    public ResponseEntity<List<Post>> getAllPosts(@RequestBody ApproveStatus approveStatus){
        List<Post> posts = adminService.getPostsByApproveStatus(approveStatus);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/member")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/member")
    public ResponseEntity<List<User>> searchUserByEmail(@RequestBody String email){
        List<User> users = adminService.searchUserByEmail(email);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/member/{id}")
    public ResponseEntity<User> changeUserRole(@PathVariable Long id, @RequestBody UserRoleDto roleDto){
        User updateUser = adminService.changeUserRole(id, roleDto.getRole());
        if(updateUser == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updateUser);
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<Post> changePostApproveStatus(@PathVariable Integer id, @RequestBody ApproveStatus approveStatus){
        Post updatePost = adminService.changePostApproveStatus(id, approveStatus);
        if(updatePost == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatePost);
    }

    @PostMapping("/notice")
    public ResponseEntity<Notice> createNotice(@RequestBody NoticeDto noticeDto) {
        Notice notice = new Notice();
//        notice.setUserId(noticeDto.getUserId());
        notice.setTitle(noticeDto.getTitle());
        notice.setContent(noticeDto.getContent());
//        notice.setCreatedAt(new Timestamp(System.currentTimeMillis()));
//        notice.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        Notice savedNotice = adminService.saveNotice(notice);
        return ResponseEntity.ok(savedNotice);
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
