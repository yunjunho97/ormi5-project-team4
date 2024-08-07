package com.example.ormi5projectteam4.repository;

import com.example.ormi5projectteam4.domain.entity.Notice;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Page<Notice> findAll(Pageable pageable);
    List<Notice> findTop5ByOrderByCreatedAtDesc();
}