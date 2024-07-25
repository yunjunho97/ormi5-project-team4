package com.example.ormi5projectteam4.domain;

import com.example.ormi5projectteam4.domain.constant.AdoptionStatus;
import com.example.ormi5projectteam4.domain.constant.ApproveStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String fountAt;
    private String fountLocation;
    private String detail;
    private String contact;
    private String tempoLocation;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private AdoptionStatus adoptionStatus;

    @Enumerated(EnumType.STRING)
    private ApproveStatus approveStatus;

    @OneToOne
    @JoinColumn(name = "animalId")
    private Animal animal;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "userId")
//    private User user;

    public void addImage(Image image) {
        images.add(image);
        image.setPost(this);
    }
}
//enum AdoptionStatus {
//    POSTING, PROCEEDING, ADOPTED
//}
//
//enum ApproveStatus {
//    PENDING, APPROVED, DENIED
//}
