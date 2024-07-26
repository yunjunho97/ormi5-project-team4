package com.example.ormi5projectteam4.service;

import com.example.ormi5projectteam4.domain.entity.Notice;
import com.example.ormi5projectteam4.domain.entity.User;
import com.example.ormi5projectteam4.repository.NoticeRepository;
//import com.example.ormi5projectteam4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ormi5projectteam4.domain.constant.Role;

import java.util.List;

@Service
public class AdminService {
//    @Autowired
//    UserRepository userRepository;

    @Autowired
    NoticeRepository noticeRepository;

//    public User changeUserRole(Integer id, Role role){
//        User user = userRepository.findById(id).orElse(null);
//        if(user != null){
//            user.setRole(role);
//            userRepository.save(user);
//        }
//        return user;
//    }

    public Notice saveNotice(Notice notice){
        return noticeRepository.save(notice);
    }

    public void deleteNotice(Integer id){
//        noticeRepository.deleteById(id);
    }

//    public Notice getNoticeById(Integer id){
//        return noticeRepository.findById(id).orElse(null);
//    }
    public List<Notice> getAllNotices(){
        return noticeRepository.findAll();
    }
}
