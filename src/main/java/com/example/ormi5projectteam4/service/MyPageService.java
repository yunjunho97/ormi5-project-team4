package com.example.ormi5projectteam4.service;

import com.example.ormi5projectteam4.domain.dto.MyPageDTO;
import com.example.ormi5projectteam4.domain.dto.PostDTO;
import com.example.ormi5projectteam4.domain.entity.User;
import com.example.ormi5projectteam4.repository.MyPageRepository;
import com.example.ormi5projectteam4.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MyPageService {

    private final MyPageRepository myPageRepository;
    private final PostRepository postRepository;



    @Autowired
    public MyPageService(MyPageRepository myPageRepository, PostRepository postRepository) {
        this.myPageRepository = myPageRepository;
        this.postRepository = postRepository;
    }

    public Optional<MyPageDTO> getUserInfo(Long userId) {
        return myPageRepository.findById(userId)
                .map(user -> {
                    MyPageDTO myPageDTO = new MyPageDTO();
                    myPageDTO.setId(user.getId());
                    myPageDTO.setUserName(user.getUserName());
                    myPageDTO.setEmail(user.getEmail());
                    myPageDTO.setPhone(user.getPhone());
                    myPageDTO.setCreatedAt(user.getCreatedAt());
                    myPageDTO.setUpdatedAt(user.getUpdatedAt());
                    return myPageDTO;
                });
    }

    @Transactional
    public void updateUserInfo(Long userId, MyPageDTO myPageDTO) {
        User user = myPageRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setUserName(myPageDTO.getUserName());
        user.setEmail(myPageDTO.getEmail());
        user.setPhone(myPageDTO.getPhone());
    }

    @Transactional
    public void deleteUser(Long userId) {
        myPageRepository.deleteById(userId);
    }

    public List<PostDTO> getPostsByUser(Long userId) {
        return postRepository.findByUserId(userId).stream()
                .map(PostDTO::fromPost)
                .collect(Collectors.toList());
    }

    public Optional<PostDTO> getMyPostDetail(Long postId, Long userId) {
        return postRepository.findById(postId)
                .filter(post -> post.getUser().getId().equals(userId))
                .map(PostDTO::fromPost);
    }

    public long getPostCountByUser(Long userId) {
        return postRepository.countByUserId(userId);
    }
}

