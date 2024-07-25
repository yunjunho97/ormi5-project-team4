package com.example.ormi5projectteam4.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoticeDto {
    private Integer userId;
    private String title;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
