package com.example.ormi5projectteam4.service;

import com.example.ormi5projectteam4.domain.constant.Role;
import com.example.ormi5projectteam4.domain.dto.*;
import com.example.ormi5projectteam4.domain.entity.PasswordQuestion;
import com.example.ormi5projectteam4.domain.entity.User;
import com.example.ormi5projectteam4.repository.PasswordQuestionRepository;
import com.example.ormi5projectteam4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
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

  /** 이메일 & 비밀번호로 유저 조회 */
  public UserDto findUserByEmailAndPw(String email, String password) {
    return userRepository.findByEmailAndPassword(email, password).stream()
        .map(UserService::convertToUserDto)
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
  }

  /** 이메일 중복 확인 */
  public int validateDuplicateEmail(String email) {
    List<User> users = userRepository.findByEmail(email);

    return users.size();
  }

  /** 닉네임 중복 확인 */
  public int validateDuplicateUserName(String userName) {
    List<User> users = userRepository.findByUserName(userName);

    return users.size();
  }

  /** 비밀번호 찾기 질문 리스트 조회 */
  public List<PasswordQuestionDto> getAllPasswordQuestions() {
    return passwordQuestionRepository.findAll().stream()
        .map(UserService::convertToPasswordQuestionDto)
        .collect(Collectors.toList());
  }

  /** 비밀번호 찾기 */
  public UserDto validateUserForPasswordChange(
      PasswordCertificationRequestDto passwordCertificationRequestDto) {
    PasswordQuestion passwordQuestion =
        findPasswordQuestionById(passwordCertificationRequestDto.getPasswordQuestionId());
    String email = passwordCertificationRequestDto.getEmail();
    String passwordAnswer = passwordCertificationRequestDto.getPasswordAnswer();

    return userRepository
        .findByEmailAndPasswordQuestionAndPasswordAnswer(email, passwordQuestion, passwordAnswer)
        .stream()
        .map(UserService::convertToUserDto)
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
  }

  /** 비밀번호 재설정 */
  public void changePassword(ChangePasswordRequestDto changePasswordRequestDto) {
    User user =
        userRepository
            .findById(changePasswordRequestDto.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

    user.setPassword(changePasswordRequestDto.getPassword());
    user.setUpdatedAt(LocalDateTime.now());
  }

  /**
   * entity -> dto
   *
   * @param passwordQuestion
   * @return PasswordQuestionDto
   */
  private static PasswordQuestionDto convertToPasswordQuestionDto(
      PasswordQuestion passwordQuestion) {
    return new PasswordQuestionDto(passwordQuestion.getId(), passwordQuestion.getQuestion());
  }

  /**
   * dto -> entity
   *
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
   * entity -> dto
   *
   * @param user
   * @return UserDto
   */
  private static UserDto convertToUserDto(User user) {
    UserDto userDto = new UserDto();
    userDto.setId(user.getId());
    userDto.setUserName(user.getUserName());
    userDto.setEmail(user.getEmail());
    userDto.setPhone(user.getPhone());
    userDto.setRole(user.getRole());

    return userDto;
  }

  /**
   * 비밀번호 찾기 질문 id로 검색
   *
   * @param id
   * @return PasswordQuestion
   */
  private PasswordQuestion findPasswordQuestionById(Long id) {
    return passwordQuestionRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("비밀번호 찾기 질문을 찾을 수 없습니다."));
  }
}
