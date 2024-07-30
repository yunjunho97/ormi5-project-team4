package com.example.ormi5projectteam4.repository;

import com.example.ormi5projectteam4.domain.entity.Notice;
import com.example.ormi5projectteam4.domain.dto.NoticeDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findTop5ByOrderByCreatedAtDesc();
}