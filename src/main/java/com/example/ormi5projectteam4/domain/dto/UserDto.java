package com.example.ormi5projectteam4.domain.dto;

import com.example.ormi5projectteam4.domain.constant.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDto {
    public UserDto(String email, Role role) {
        this.email = email;
        this.role = role;
    }

    private Long id;

    private String email;

    private String userName;

    private String password;

    private Long passwordQuestionId;

    private String passwordAnswer;

    private String phone;

    private Role role;

    private LocalDateTime createdAt;
}
