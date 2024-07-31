package com.example.ormi5projectteam4.service;

import com.example.ormi5projectteam4.domain.constant.Role;
import com.example.ormi5projectteam4.domain.dto.UserDto;
import com.example.ormi5projectteam4.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

  /** 현재 사용자 세션 정보를 저장 */
  private static final ThreadLocal<Optional<UserDto>> currentUser =
      ThreadLocal.withInitial(Optional::empty);

  /**
   * 사용자 인증 로직(로그인 시 호출)
   *
   * @param user
   */
  public void authenticate(UserDto user) {
    currentUser.set(Optional.of(user));
  }

  /**
   * 현재 인증된 사용자 반환
   *
   * @return Optional<User>
   */
  public Optional<UserDto> getAuthenticatedUser() {
    return currentUser.get();
  }

  /** 로그아웃 */
  public void logout() {
    currentUser.remove();
  }


  /**
   * 사용자가 특정 권한을 가지고 있는지 확인하는 로직
   * @param role
   * @return boolean
   */
  public boolean hasRole(Role role) {
    return currentUser.get().map(user -> user.getRole().equals(role)).orElse(false);
  }
}
