package com.example.ormi5projectteam4.domain.dto;

import lombok.Data;

@Data
public class UserDto {
    private String email;

    private String userName;

    private String password;

    private Long passwordQuestionId;

    private String passwordAnswer;

    private String phone;
}
