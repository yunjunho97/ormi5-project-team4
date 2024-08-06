package com.example.ormi5projectteam4.controller.rest_controller;

import com.example.ormi5projectteam4.annotation.Secured;
import com.example.ormi5projectteam4.domain.constant.AdoptionStatus;
import com.example.ormi5projectteam4.domain.constant.ApproveStatus;
import com.example.ormi5projectteam4.domain.constant.Role;
import com.example.ormi5projectteam4.domain.dto.PostDTO;
import com.example.ormi5projectteam4.domain.dto.UserManagementDto;
import com.example.ormi5projectteam4.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
  @Autowired private AdminService adminService;

  @Secured(role = Role.ADMIN)
  @GetMapping("/post")
  public ResponseEntity<Page<PostDTO>> getPostsByConditions(
          @RequestParam(required = false) ApproveStatus approveStatus,
          @RequestParam(required = false) AdoptionStatus adoptionStatus,
          @RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "6") int size) {
    Page<PostDTO> posts = adminService.getPostsByConditions(approveStatus, adoptionStatus, page, size);

    return ResponseEntity.ok(posts);
  }

  @Secured(role = Role.ADMIN)
  @GetMapping("/member")
  public ResponseEntity<Page<UserManagementDto>> getUsersByConditions(
      @RequestParam(required = false) String email,
      @RequestParam (defaultValue = "0") int page,
      @RequestParam(defaultValue = "17") int size) {

    Page<UserManagementDto> users = adminService.getUsersByConditions(email, page, size);

    return ResponseEntity.ok(users);
  }

  @Secured(role = Role.ADMIN)
  @PutMapping("/member/{id}")
  public ResponseEntity<UserManagementDto> changeUserRole(
      @PathVariable Long id, @RequestParam Role role) {
    UserManagementDto updateUser = adminService.changeUserRole(id, role);
    if (updateUser == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(updateUser);
  }

  @Secured(role = Role.ADMIN)
  @PutMapping("/post/{id}")
  public ResponseEntity<PostDTO> changePostApproveStatus(
      @PathVariable Long id, @RequestParam ApproveStatus approveStatus) {
    PostDTO updatePost = adminService.changePostApproveStatus(id, approveStatus);
    if (updatePost == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(updatePost);
  }
}
