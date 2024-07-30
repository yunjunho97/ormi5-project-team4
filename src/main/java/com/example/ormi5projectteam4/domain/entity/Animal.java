package com.example.ormi5projectteam4.domain.entity;

import com.example.ormi5projectteam4.domain.constant.Gender;
import com.example.ormi5projectteam4.domain.constant.NeuteringStatus;
import com.example.ormi5projectteam4.domain.constant.Species;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "animals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String breed;
    private Integer age;
    private Integer weight;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private NeuteringStatus neuteringStatus;

    @Enumerated(EnumType.STRING)
    private Species species;
}
//enum Gender {
//    MALE, FEMALE
//}

//enum NeuteringStatus {
//    COMPLETE, INCOMPLETE, UNKNOWN
//}
//
//enum Species {
//    DOG, CAT, ETC
//}