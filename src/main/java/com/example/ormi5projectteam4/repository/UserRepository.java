package com.example.ormi5projectteam4.repository;

import com.example.ormi5projectteam4.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
