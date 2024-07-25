package com.example.ormi5projectteam4.repository;

import com.example.ormi5projectteam4.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
