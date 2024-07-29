package com.example.ormi5projectteam4.repository;

import com.example.ormi5projectteam4.domain.constant.ApproveStatus;
import com.example.ormi5projectteam4.domain.dto.PostDTO;
import com.example.ormi5projectteam4.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Page<Post> findAll(Pageable pageable);

    Page<Post> findByFoundLocation(String foundLocation, Pageable pageable);

    List<Post> findByApproveStatus(ApproveStatus approveStatus);
}
