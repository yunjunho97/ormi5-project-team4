package com.example.Repository;

import com.example.domain.Notice;
import com.example.domain.NoticeDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<NoticeDto> findTop5ByOrderByCreatedAtDesc();
}