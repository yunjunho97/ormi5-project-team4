package com.example.ormi5projectteam4.controller.rest_controller;

import com.example.ormi5projectteam4.domain.constant.StatusCode;
import com.example.ormi5projectteam4.domain.constant.StatusMessage;
import com.example.ormi5projectteam4.domain.dto.*;
import com.example.ormi5projectteam4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.processing.Find;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  /** 회원가입 */
  @PostMapping("/member")
  public ResponseEntity<JoinResponseDto> generateMember(
      @RequestBody JoinRequestDto joinRequestDto) {
    Long generatedUserId = userService.join(joinRequestDto);
    JoinResponseDto joinResponseDto = new JoinResponseDto(generatedUserId);

    return new ResponseEntity<>(joinResponseDto, HttpStatus.valueOf(StatusCode.SUCCESS));
  }

  /** 이메일 중복확인 */
  @GetMapping("/email-duplication")
  public ResponseEntity<String> validateDuplicateUserEmail(@RequestParam String email) {
    int result = userService.validateDuplicateEmail(email);
    ResponseEntity<String> response;

    if (result == 0) {
      response = new ResponseEntity<>(HttpStatus.valueOf(StatusCode.NO_CONTENT));
    } else {
      response =
          new ResponseEntity<>(
              StatusMessage.DUPLICATED_USER, HttpStatus.valueOf(StatusCode.DUPLICATED_USER));
    }

    return response;
  }

  /** 닉네임 중복확인 */
  @GetMapping("/nickname-duplication")
  public ResponseEntity<String> validateDuplicateUserName(@RequestParam String userName) {
    int result = userService.validateDuplicateUserName(userName);
    ResponseEntity<String> response;

    if (result == 0) {
      response = new ResponseEntity<>(HttpStatus.valueOf(StatusCode.NO_CONTENT));
    } else {
      response =
          new ResponseEntity<>(
              StatusMessage.DUPLICATED_USER, HttpStatus.valueOf(StatusCode.DUPLICATED_USER));
    }

    return response;
  }

  /** 비밀번호 찾기 질문 조회 */
  @GetMapping("/password-question")
  public ResponseEntity<List<PasswordQuestionDto>> getAllPasswordQuestions() {
    List<PasswordQuestionDto> list = userService.getAllPasswordQuestions();

    return new ResponseEntity<>(list, HttpStatus.valueOf(StatusCode.SUCCESS));
  }

  /** 회원탈퇴 */
  @DeleteMapping("/member/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUserById(id);

    return new ResponseEntity<>(HttpStatus.valueOf(StatusCode.NO_CONTENT));
  }
}
