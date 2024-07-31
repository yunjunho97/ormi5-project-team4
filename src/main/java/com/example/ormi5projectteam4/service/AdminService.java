package com.example.ormi5projectteam4.service;

import com.example.ormi5projectteam4.domain.constant.ApproveStatus;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    NoticeRepository noticeRepository;

    public Page<Post> getPostsByApproveStatus(ApproveStatus approveStatus, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        if (approveStatus == null) {
            return postRepository.findAll(pageable);
        } else {
            return postRepository.findByApproveStatus(approveStatus, pageable);
        }
    }

    public List<UserManagementDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::userConvertToUserManagementDto).collect(Collectors.toList());
    }

    public List<UserManagementDto> searchUserByEmail(String email) {
        return userRepository.findByEmailContaining(email).stream().map(this::userConvertToUserManagementDto).collect(Collectors.toList());
    }

    public UserManagementDto changeUserRole(Long id, Role role) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setRole(role);
            userRepository.save(user);
        }
        return userConvertToUserManagementDto(user);
    }

    public Post changePostApproveStatus(Integer id, ApproveStatus approveStatus) {
        Post post = postRepository.findById(id).orElse(null);

        if (post != null) {
            post.setApproveStatus(approveStatus);
            postRepository.save(post);
        }

        return post;
    }

    public Notice saveNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    public void deleteNotice(Integer id) {
//        noticeRepository.deleteById(id);
    }

    //    public Notice getNoticeById(Integer id){
//        return noticeRepository.findById(id).orElse(null);
//    }
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
