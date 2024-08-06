package com.example.ormi5projectteam4.domain.dto;

import com.example.ormi5projectteam4.domain.constant.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import utils.enums.Role;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRoleDto {
    private Integer userId;
    private Role role;
}
