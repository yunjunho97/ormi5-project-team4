package com.example.ormi5projectteam4.service;

import com.example.ormi5projectteam4.domain.constant.AdoptionStatus;
import com.example.ormi5projectteam4.domain.constant.ApproveStatus;
import com.example.ormi5projectteam4.domain.dto.PostDTO;
import com.example.ormi5projectteam4.domain.dto.UserManagementDto;
import com.example.ormi5projectteam4.domain.entity.Notice;
import com.example.ormi5projectteam4.domain.entity.Post;
import com.example.ormi5projectteam4.domain.entity.User;
import com.example.ormi5projectteam4.repository.NoticeRepository;
import com.example.ormi5projectteam4.repository.PostRepository;
import com.example.ormi5projectteam4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.ormi5projectteam4.domain.constant.Role;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    NoticeRepository noticeRepository;

    public Page<PostDTO> getPostsByConditions(ApproveStatus approveStatus, AdoptionStatus adoptionStatus, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        if (approveStatus != null && adoptionStatus != null) {
            return postRepository.findByApproveStatusAndAdoptionStatus(approveStatus, adoptionStatus, pageable).map(PostDTO::fromPost);
        } else if (approveStatus != null) {
            return postRepository.findByApproveStatus(approveStatus, pageable).map(PostDTO::fromPost);
        } else if (adoptionStatus != null) {
            return postRepository.findByAdoptionStatus(adoptionStatus, pageable).map(PostDTO::fromPost);
        } else {
            return postRepository.findAll(pageable).map(PostDTO::fromPost);
        }
    }

    public Page<UserManagementDto> getUsersByConditions(String email, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        if (email == null) {
            return userRepository.findAll(pageable).map(this::userConvertToUserManagementDto);
        } else {
            return userRepository.findByEmailContaining(email, pageable).map(this::userConvertToUserManagementDto);
        }
    }

    public UserManagementDto changeUserRole(Long id, Role role) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setRole(role);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        return userConvertToUserManagementDto(user);
    }

    public PostDTO changePostApproveStatus(Long id, ApproveStatus approveStatus) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found"));
        post.setApproveStatus(approveStatus);
        post.setUpdatedAt(LocalDateTime.now());
        postRepository.save(post);

        return PostDTO.fromPost(post);
    }

    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }

    private UserManagementDto userConvertToUserManagementDto(User user) {
        UserManagementDto userManagementDto = new UserManagementDto();
        userManagementDto.setEmail(user.getEmail());
        userManagementDto.setPhone(user.getPhone());
        userManagementDto.setRole(user.getRole());
        userManagementDto.setPassword(user.getPassword());
        userManagementDto.setPasswordQuestionId(user.getPasswordQuestion().getId());
        userManagementDto.setPasswordAnswer(user.getPasswordAnswer());
        userManagementDto.setCreatedAt(user.getCreatedAt());
        userManagementDto.setUpdatedAt(user.getUpdatedAt());
        return userManagementDto;
    }
}
