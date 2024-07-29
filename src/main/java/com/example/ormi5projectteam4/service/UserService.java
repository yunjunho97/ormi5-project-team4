package com.example.ormi5projectteam4.service;

import com.example.ormi5projectteam4.domain.constant.Role;
import com.example.ormi5projectteam4.domain.dto.EmailDuplicationRequestDto;
import com.example.ormi5projectteam4.domain.dto.JoinRequestDto;
import com.example.ormi5projectteam4.domain.dto.UserDto;
import com.example.ormi5projectteam4.domain.dto.UserNameDuplicationRequestDto;
import com.example.ormi5projectteam4.domain.entity.PasswordQuestion;
import com.example.ormi5projectteam4.domain.entity.User;
import com.example.ormi5projectteam4.repository.PasswordQuestionRepository;
import com.example.ormi5projectteam4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordQuestionRepository passwordQuestionRepository;

  /** 회원가입 */
  public Long join(JoinRequestDto joinRequestDto) {
    User user = convertToUserEntity(joinRequestDto);
    user.setRole(Role.ACTIVE);
    user.setCreatedAt(LocalDateTime.now());
    userRepository.save(user);

    return user.getId();
  }

  /** 로그인 */

  /** 로그아웃 */

  /** 이메일 중복 확인 */
  public int validateDuplicateEmail(EmailDuplicationRequestDto emailDuplicationRequestDto) {
    List<User> users = userRepository.findByEmail(emailDuplicationRequestDto.getEmail());

    return users.size();
  }

  /** 닉네임 중복 확인 */
  public int validateDuplicateUserName(UserNameDuplicationRequestDto userNameDuplicationRequestDto) {
    List<User> users = userRepository.findByUserName(userNameDuplicationRequestDto.getUserName());

    return users.size();
  }

  /** 비밀번호 찾기 */

  /** 비밀번호 재설정 */

  /**
   * dto -> entity
   * @param userDto
   * @return User
   */
  private User convertToUserEntity(UserDto userDto) {
    User user = new User();
    user.setEmail(userDto.getEmail());
    user.setUserName(userDto.getUserName());
    user.setPassword(userDto.getPassword());
    user.setPasswordAnswer(userDto.getPasswordAnswer());
    user.setPhone(userDto.getPhone());

    PasswordQuestion passwordQuestion = findPasswordQuestionById(userDto.getPasswordQuestionId());
    user.setPasswordQuestion(passwordQuestion);

    return user;
  }

  /**
   * 비밀번호 찾기 질문 id로 검색
   * @param id
   * @return PasswordQuestion
   */
  private PasswordQuestion findPasswordQuestionById(Long id) {
    return passwordQuestionRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("비밀번호 찾기 질문을 찾을 수 없습니다."));
  }
}
