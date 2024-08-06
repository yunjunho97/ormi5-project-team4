package com.example.ormi5projectteam4.service;

import com.example.ormi5projectteam4.domain.entity.User;
import com.example.ormi5projectteam4.repository.NoticeRepository;
import com.example.ormi5projectteam4.domain.entity.Notice;
import com.example.ormi5projectteam4.domain.dto.NoticeDto;
import com.example.ormi5projectteam4.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository, UserRepository userRepository) {
        this.noticeRepository = noticeRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public NoticeDto createNotice(NoticeDto noticeDto) {
        Notice notice = NoticeDto.toEntity(noticeDto);
        notice.setCreatedAt(LocalDateTime.now());
        notice.setUpdatedAt(LocalDateTime.now());

        Notice savedNotice = noticeRepository.save(notice);

        // user 세팅
        Long userId = noticeDto.getUserId();
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        user.addNotice(savedNotice);

        return NoticeDto.fromEntity(savedNotice);
    }

    @Transactional(readOnly = true)
    public List<NoticeDto> getAllNotices() {
        return noticeRepository.findAll().stream()
                .map(NoticeDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<NoticeDto> getAllNoticesDesc(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        return noticeRepository.findAll(pageable).map(NoticeDto::fromEntity);
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

    @Transactional(readOnly = true)
    public List<NoticeDto> getLatestNotices() {
        return noticeRepository.findTop5ByOrderByCreatedAtDesc().stream()
                .map(NoticeDto::fromEntity)
                .collect(Collectors.toList());
    }
}