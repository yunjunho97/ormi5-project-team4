package com.example.ormi5projectteam4.controller.rest_controller;

import com.example.ormi5projectteam4.domain.constant.StatusCode;
import com.example.ormi5projectteam4.domain.dto.EmailDuplicationRequestDto;
import com.example.ormi5projectteam4.domain.dto.JoinRequestDto;
import com.example.ormi5projectteam4.domain.dto.JoinResponseDto;
import com.example.ormi5projectteam4.domain.dto.UserNameDuplicationRequestDto;
import com.example.ormi5projectteam4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping("/member")
  public ResponseEntity<JoinResponseDto> generateMember(
      @RequestBody JoinRequestDto joinRequestDto) {
    Long generatedUserId = userService.join(joinRequestDto);
    JoinResponseDto joinResponseDto = new JoinResponseDto(generatedUserId);

    return new ResponseEntity<>(HttpStatus.valueOf(StatusCode.SUCCESS));
  }

  @GetMapping("/email-duplication")
  public ResponseEntity<Void> validateDuplicateUserEmail(
      @RequestParam EmailDuplicationRequestDto emailDuplicationRequestDto) {
    int result = userService.validateDuplicateEmail(emailDuplicationRequestDto);
    ResponseEntity<Void> response;

    if (result != 0) {
      response = new ResponseEntity<>(HttpStatus.valueOf(StatusCode.NO_CONTENT));
    } else {
      response = new ResponseEntity<>(HttpStatus.valueOf(StatusCode.DUPLICATED_USER));
    }

    return response;
  }

  @GetMapping("/nickname-duplication")
  public ResponseEntity<Void> validateDuplicateUserName(
          @RequestParam UserNameDuplicationRequestDto userNameDuplicationRequestDto) {
    int result = userService.validateDuplicateUserName(userNameDuplicationRequestDto);
    ResponseEntity<Void> response;

    if (result != 0) {
      response = new ResponseEntity<>(HttpStatus.valueOf(StatusCode.NO_CONTENT));
    } else {
      response = new ResponseEntity<>(HttpStatus.valueOf(StatusCode.DUPLICATED_USER));
    }

    return response;
  }
}
