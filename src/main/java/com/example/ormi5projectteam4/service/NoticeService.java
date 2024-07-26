package com.example.ormi5projectteam4.service;

import com.example.ormi5projectteam4.repository.NoticeRepository;
import com.example.ormi5projectteam4.domain.entity.Notice;
import com.example.ormi5projectteam4.domain.dto.NoticeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Transactional
    public NoticeDto createNotice(NoticeDto noticeDto) {
        Notice notice = noticeDto.toEntity();
        Notice savedNotice = noticeRepository.save(notice);
        return NoticeDto.fromEntity(savedNotice);
    }

    @Transactional(readOnly = true)
    public List<NoticeDto> getAllNotices() {
        return noticeRepository.findAll().stream()
                .map(NoticeDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<NoticeDto> getNoticeById(Long id) {
        return noticeRepository.findById(id)
                .map(NoticeDto::fromEntity);
    }

    @Transactional
    public Optional<NoticeDto> updateNotice(Long id, NoticeDto noticeDto) {
        return noticeRepository.findById(id)
                .map(existingNotice -> {
                    existingNotice.setTitle(noticeDto.getTitle());
                    existingNotice.setContent(noticeDto.getContent());
                    existingNotice.setUpdatedAt(LocalDateTime.now());
                    return NoticeDto.fromEntity(noticeRepository.save(existingNotice));
                });
    }

    @Transactional
    public boolean deleteNotice(Long id) {
        return noticeRepository.findById(id)
                .map(notice -> {
                    noticeRepository.delete(notice);
                    return true;
                })
                .orElse(false);
    }

//    public List<NoticeDto> getLatestNotices() {
//        return noticeRepository.findTop5ByOrderByCreatedAtDesc().stream()
//                .map(NoticeDto::fromEntity)
//                .collect(Collectors.toList());
//    }
}