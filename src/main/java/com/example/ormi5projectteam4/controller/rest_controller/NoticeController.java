package com.example.ormi5projectteam4.controller.rest_controller;

import com.example.ormi5projectteam4.domain.constant.AdoptionStatus;
import com.example.ormi5projectteam4.domain.constant.ApproveStatus;
import com.example.ormi5projectteam4.domain.dto.NoticeDto;
import com.example.ormi5projectteam4.domain.dto.PostDTO;
import com.example.ormi5projectteam4.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoticeController {
    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    // CREATE: 새 공지사항 추가
    @PostMapping("/admin/notice")
    public ResponseEntity<NoticeDto> createNotice(@RequestBody NoticeDto noticeDto) {
        NoticeDto createdNotice = noticeService.createNotice(noticeDto);
        return new ResponseEntity<>(createdNotice, HttpStatus.CREATED);
    }

    // READ: 모든 공지사항 조회
    @GetMapping("/admin/notice")
    public ResponseEntity<Page<NoticeDto>> getNoticesSortDesc(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "14") int size) {

        Page<NoticeDto> notices = noticeService.getAllNoticesDesc(page, size);
        return ResponseEntity.ok(notices);
    }

    //@GetMapping("/admin/notice")
    //public ResponseEntity<List<NoticeDto>> getAllNotices() {
    //    List<NoticeDto> notices = noticeService.getAllNotices();
    //    return ResponseEntity.ok(notices);
    //}

    // READ: 특정 ID의 공지사항 조회
    @GetMapping("/admin/notice/{id}")
    public ResponseEntity<NoticeDto> getNoticeById(@PathVariable Long id) {
        return noticeService.getNoticeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE: 공지사항 정보 업데이트
    @PutMapping("/admin/notice/{id}")
    public ResponseEntity<NoticeDto> updateNotice(@PathVariable Long id, @RequestBody NoticeDto noticeDto) {
        return noticeService.updateNotice(id, noticeDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: 공지사항 삭제
    @DeleteMapping("/admin/notice/{id}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long id) {
        boolean deleted = noticeService.deleteNotice(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // 사용자 화면에서 처리
    // READ: 최신 공지사항 조회
    @GetMapping("/notice")
    public ResponseEntity<List<NoticeDto>> getLatestNotices() {
        List<NoticeDto> latestNotices = noticeService.getLatestNotices();
        return ResponseEntity.ok(latestNotices);
    }

}