package com.example.ormi5projectteam4.repository;

import com.example.ormi5projectteam4.domain.entity.PasswordQuestion;
import com.example.ormi5projectteam4.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
  List<User> findByEmail(String email);

  List<User> findByUserName(String userName);

  List<User> findByEmailAndPasswordQuestionAndPasswordAnswer(
      String email, PasswordQuestion passwordQuestion, String passwordAnswer);
  
  List<User> findByEmailContaining(String email);
}
