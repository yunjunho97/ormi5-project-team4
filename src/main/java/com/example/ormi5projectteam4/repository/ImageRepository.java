package com.example.ormi5projectteam4.repository;

import com.example.ormi5projectteam4.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
