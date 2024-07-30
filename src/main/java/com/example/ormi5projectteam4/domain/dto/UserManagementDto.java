package com.example.ormi5projectteam4.domain.dto;

import com.example.ormi5projectteam4.domain.constant.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserManagementDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userName;
    private String email;
    private String password;
    private String passwordAnswer;
    private String phone;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long passwordQuestionId;
}