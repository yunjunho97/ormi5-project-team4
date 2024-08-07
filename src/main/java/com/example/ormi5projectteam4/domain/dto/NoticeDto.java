package com.example.ormi5projectteam4.domain.dto;

import com.example.ormi5projectteam4.domain.entity.Notice;
import com.example.ormi5projectteam4.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeDto {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static NoticeDto fromEntity(Notice notice) {
        return NoticeDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .createdAt(notice.getCreatedAt())
                .updatedAt(notice.getUpdatedAt())
                .build();
    }

    public static Notice toEntity(NoticeDto noticeDto) {
        Notice notice = new Notice();
        notice.setId(noticeDto.getId());
        notice.setTitle(noticeDto.getTitle());
        notice.setContent(noticeDto.getContent());
        notice.setCreatedAt(noticeDto.getCreatedAt());
        notice.setUpdatedAt(noticeDto.getUpdatedAt());
        return notice;
        //return new Notice(
        //        this.id,
        //        this.title,
        //        this.content,
        //        this.createdAt,
        //        this.updatedAt
        //);
    }
}