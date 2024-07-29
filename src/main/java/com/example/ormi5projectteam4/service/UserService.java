package com.example.ormi5projectteam4.service;

import com.example.ormi5projectteam4.domain.dto.MyPageDTO;
import com.example.ormi5projectteam4.domain.entity.User;
import com.example.ormi5projectteam4.repository.MyPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final MyPageRepository myPageRepository;

    @Autowired
    public UserService(MyPageRepository myPageRepository) {
        this.myPageRepository = myPageRepository;
    }

    public MyPageDTO getUserInfo(Long userId) {
        Optional<User> userOptional = myPageRepository.findById(userId);
        return userOptional.map(user -> {
            MyPageDTO myPageDTO = new MyPageDTO();
            myPageDTO.setId(user.getId());
            myPageDTO.setUserName(user.getUserName());
            myPageDTO.setEmail(user.getEmail());
            myPageDTO.setPhone(user.getPhone());
            myPageDTO.setCreatedAt(user.getCreatedAt());
            myPageDTO.setUpdatedAt(user.getUpdatedAt());
            return myPageDTO;
        }).orElse(null);
    }

    @Transactional
    public void updateUserInfo(Long userId, MyPageDTO myPageDTO) {
        myPageRepository.findById(userId).ifPresent(user -> {
            user.setUserName(myPageDTO.getUserName());
            user.setEmail(myPageDTO.getEmail());
            user.setPhone(myPageDTO.getPhone());
        });
    }

    @Transactional
    public void deleteUser(Long userId) {
        myPageRepository.deleteById(userId);
    }
}