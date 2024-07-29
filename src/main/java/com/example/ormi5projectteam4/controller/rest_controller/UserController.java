package com.example.ormi5projectteam4.controller.rest_controller;

import com.example.ormi5projectteam4.domain.dto.JoinRequestDto;
import com.example.ormi5projectteam4.domain.dto.JoinResponseDto;
import com.example.ormi5projectteam4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/member")
    public JoinResponseDto generateMember(@RequestBody JoinRequestDto joinRequestDto) {
        Long generatedUserId = userService.join(joinRequestDto);
        return new JoinResponseDto(generatedUserId);
    }
}
