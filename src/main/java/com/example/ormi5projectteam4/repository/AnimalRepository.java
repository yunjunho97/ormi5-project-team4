package com.example.ormi5projectteam4.repository;

import com.example.ormi5projectteam4.domain.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
