package com.example.ormi5projectteam4.domain.dto;

import com.example.ormi5projectteam4.domain.entity.Notice;
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
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;

    public static NoticeDto fromEntity(Notice notice) {
        return NoticeDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
//                .createdAt(notice.getCreatedAt().toString())
//                .updatedAt(notice.getUpdatedAt().toString())
//                .userId(notice.getUserId().getId())
                .build();
    }

    public Notice toEntity() {
        return new Notice(
                this.id,
                this.title,
                this.content,
                this.createdAt,
                this.updatedAt
//                this.userId
        );
    }
}