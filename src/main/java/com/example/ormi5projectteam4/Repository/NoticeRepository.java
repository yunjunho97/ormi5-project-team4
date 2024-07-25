package com.example.ormi5projectteam4.Repository;

import com.example.ormi5projectteam4.domain.entity.Notice;
import com.example.ormi5projectteam4.domain.dto.NoticeDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<NoticeDto> findTop5ByOrderByCreatedAtDesc();
}