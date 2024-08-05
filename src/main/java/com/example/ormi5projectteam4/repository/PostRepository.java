package com.example.ormi5projectteam4.repository;

import com.example.ormi5projectteam4.domain.constant.AdoptionStatus;
import com.example.ormi5projectteam4.domain.constant.ApproveStatus;
import com.example.ormi5projectteam4.domain.dto.PostDTO;
import com.example.ormi5projectteam4.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAll(Pageable pageable);

    Page<Post> findByFoundLocation(String foundLocation, Pageable pageable);

    Page<Post> findByApproveStatus(ApproveStatus approveStatus, Pageable pageable);

    Page<Post> findByAdoptionStatus(AdoptionStatus approveStatus, Pageable pageable);

    Page<Post> findByApproveStatusAndAdoptionStatus(ApproveStatus approveStatus, AdoptionStatus adoptionStatus, Pageable pageable);

    List<Post> findByUserId(Long userId);

    long countByUserId(Long userId);

    Page<Post> findByFoundLocationContains(String foundLocation, Pageable pageable);
}
