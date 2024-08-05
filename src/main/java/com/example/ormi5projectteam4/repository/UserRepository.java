package com.example.ormi5projectteam4.repository;

import com.example.ormi5projectteam4.domain.entity.PasswordQuestion;
import com.example.ormi5projectteam4.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
  Page<User> findAll(Pageable pageable);

  List<User> findByEmail(String email);

  List<User> findByUserName(String userName);

  List<User> findByEmailAndPasswordQuestionAndPasswordAnswer(
      String email, PasswordQuestion passwordQuestion, String passwordAnswer);
  
  Page<User> findByEmailContaining(String email, Pageable pageable);

  List<User> findByEmailAndPassword(String email, String phone);
}
