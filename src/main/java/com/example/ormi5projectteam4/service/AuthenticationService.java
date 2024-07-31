package com.example.ormi5projectteam4.service;

import com.example.ormi5projectteam4.domain.constant.Role;
import com.example.ormi5projectteam4.domain.dto.UserDto;
import com.example.ormi5projectteam4.domain.entity.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  /** 현재 사용자 세션 정보를 저장 */
  private static final String USER_SESSION_KEY = "authenticatedUser";

  private final HttpSession session;

  /**
   * 사용자 인증 로직(로그인 시 호출)
   *
   * @param userDto
   */
  public void authenticate(UserDto userDto) {
    session.setAttribute(USER_SESSION_KEY, userDto);
  }

  /**
   * 현재 인증된 사용자 반환
   *
   * @return Optional<User>
   */
  public Optional<UserDto> getAuthenticatedUser() {
    return Optional.ofNullable((UserDto) session.getAttribute(USER_SESSION_KEY));
  }

  /** 로그아웃 */
  public void logout() {
    session.removeAttribute(USER_SESSION_KEY);
  }

  /**
   * 사용자가 특정 권한을 가지고 있는지 확인하는 로직
   *
   * @param role
   * @return boolean
   */
  public boolean hasRole(Role role) {
    return getAuthenticatedUser().map(user -> user.getRole().equals(role)).orElse(false);
  }
}
