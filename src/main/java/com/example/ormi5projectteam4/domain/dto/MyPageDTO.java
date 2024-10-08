package com.example.ormi5projectteam4.domain.dto;


import com.example.ormi5projectteam4.domain.constant.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MyPageDTO {

    private Long id;
    private String userName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String email;
    private String phone;
    private Long postCount;
    private List<PostDTO> posts;
    private Role role;

}
