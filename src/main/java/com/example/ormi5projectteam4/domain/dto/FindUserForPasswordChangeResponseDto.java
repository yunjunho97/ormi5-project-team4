package com.example.ormi5projectteam4.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class FindUserForPasswordChangeResponseDto {
    private final String statusMessage;

    private UserDto userDto;
}
