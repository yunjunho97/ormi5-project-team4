package com.example.ormi5projectteam4.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasswordQuestionDto {
    private Long id;

    private String question;
}
