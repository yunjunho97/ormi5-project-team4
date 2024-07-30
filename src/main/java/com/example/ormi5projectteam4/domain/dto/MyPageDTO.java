package com.example.ormi5projectteam4.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MyPageDTO {

    private Long id;
    private String userName;
    private Date createdAt;
    private Date updatedAt;
    private String email;
    private String phone;
    private String postCount;
    private List<PostDTO> posts;

}
