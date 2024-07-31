package com.example.ormi5projectteam4.controller.rest_controller;

import com.example.ormi5projectteam4.domain.constant.StatusCode;
import com.example.ormi5projectteam4.domain.constant.StatusMessage;
import com.example.ormi5projectteam4.domain.dto.*;
import com.example.ormi5projectteam4.service.AuthenticationService;
import com.example.ormi5projectteam4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationService authenticationService;
  private final UserService userService;

  /** 로그인 */
  @PostMapping("/login")
  public ResponseEntity<UserResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
    UserResponseDto responseDto;
    try {
      UserDto user =
          userService.findUserByEmailAndPw(
              loginRequestDto.getEmail(), loginRequestDto.getPassword());

      authenticationService.authenticate(user);
      responseDto = new UserResponseDto(StatusMessage.SUCCESS, user);

      return new ResponseEntity<>(responseDto, HttpStatus.valueOf(StatusCode.SUCCESS));
    } catch (IllegalArgumentException e) {
      responseDto = new UserResponseDto(e.getMessage());
      return new ResponseEntity<>(responseDto, HttpStatus.valueOf(StatusCode.UNAUTHORIZED_USER));
    }
  }

  /** 로그아웃 */
  @PostMapping("/logout")
  public ResponseEntity<Void> logout() {
    authenticationService.logout();
    return new ResponseEntity<>(HttpStatus.valueOf(StatusCode.NO_CONTENT));
  }

  /** 비밀번호 찾기(유저 검색) */
  @PostMapping("/password-certification")
  public ResponseEntity<UserResponseDto> findUserForPasswordChange(
      @RequestBody PasswordCertificationRequestDto passwordCertificationRequestDto) {
    UserResponseDto responseDto;

    try {
      UserDto userDto = userService.validateUserForPasswordChange(passwordCertificationRequestDto);
      responseDto = new UserResponseDto(StatusMessage.SUCCESS, userDto);
    } catch (IllegalArgumentException e) {
      responseDto = new UserResponseDto(e.getMessage());
      return new ResponseEntity<>(responseDto, HttpStatus.valueOf(StatusCode.NOT_FOUND));
    }

    return new ResponseEntity<>(responseDto, HttpStatus.valueOf(StatusCode.SUCCESS));
  }

  /** 비밀번호 변경 */
  @PutMapping("/password")
  public ResponseEntity<Void> changePassword(
      @RequestBody ChangePasswordRequestDto changePasswordRequestDto) {
    userService.changePassword(changePasswordRequestDto);

    return new ResponseEntity<>(HttpStatus.valueOf(StatusCode.NO_CONTENT));
  }
}
