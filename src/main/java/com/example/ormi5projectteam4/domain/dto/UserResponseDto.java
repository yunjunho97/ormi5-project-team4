package com.example.ormi5projectteam4.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserResponseDto {
    private final String statusMessage;

    private UserDto userDto;
}
