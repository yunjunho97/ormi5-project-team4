package com.example.ormi5projectteam4.controller.rest_controller;

import com.example.ormi5projectteam4.domain.constant.StatusCode;
import com.example.ormi5projectteam4.domain.dto.*;
import com.example.ormi5projectteam4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping("/member")
  public ResponseEntity<JoinResponseDto> generateMember(
      @RequestBody JoinRequestDto joinRequestDto) {
    Long generatedUserId = userService.join(joinRequestDto);
    JoinResponseDto joinResponseDto = new JoinResponseDto(generatedUserId);

    return new ResponseEntity<>(joinResponseDto, HttpStatus.valueOf(StatusCode.SUCCESS));
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

  @GetMapping("/password-question")
  public ResponseEntity<List<PasswordQuestionDto>> getAllPasswordQuestions() {
    List<PasswordQuestionDto> list = userService.getAllPasswordQuestions();

    return new ResponseEntity<>(list, HttpStatus.valueOf(StatusCode.SUCCESS));
  }

  @PostMapping("/auth/password-certification")
  public ResponseEntity<UserDto> findUserForPasswordChange(
      @RequestBody PasswordCertificationRequestDto passwordCertificationRequestDto) {
    UserDto userDto = userService.validateUserForPasswordChange(passwordCertificationRequestDto);

    return new ResponseEntity<>(userDto, HttpStatus.valueOf(StatusCode.SUCCESS));
  }

  @PutMapping("/auth/password")
  public ResponseEntity<Void> changePassword(
      @RequestBody ChangePasswordRequestDto changePasswordRequestDto) {
    userService.changePassword(changePasswordRequestDto);

    return new ResponseEntity<>(HttpStatus.valueOf(StatusCode.NO_CONTENT));
  }
}
