package com.example.ormi5projectteam4.domain.dto;

import lombok.Data;

@Data
public class ChangePasswordRequestDto {
    private Long userId;
    private String password;
}
