package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeDto {
    private Long id;
    private String title;
    private String content;
    private String createdAt;
    private String updatedAt;
    private Long userId;

    public static NoticeDto fromEntity(Notice notice) {
        return NoticeDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .createdAt(notice.getCreatedAt().toString())
                .updatedAt(notice.getUpdatedAt().toString())
                .userId(notice.getUserId().getId())
                .build();
    }

    public Notice toEntity() {
        return new Notice(
                this.id,
                this.title,
                this.content,
                this.createdAt,
                this.updatedAt,
                this.userId
        );
    }
}