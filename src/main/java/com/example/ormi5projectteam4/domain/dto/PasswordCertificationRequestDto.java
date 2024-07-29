package com.example.ormi5projectteam4.domain.dto;

import lombok.Data;

@Data
public class PasswordCertificationRequestDto {
    private String email;
    private Long passwordQuestionId;
    private String passwordAnswer;
}
