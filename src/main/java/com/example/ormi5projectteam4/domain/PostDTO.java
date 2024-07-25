package com.example.ormi5projectteam4.domain;

import com.example.ormi5projectteam4.domain.constant.AdoptionStatus;
import com.example.ormi5projectteam4.domain.constant.ApproveStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {
    private Integer id;
    private String title;
    private String fountAt;
    private String fountLocation;
    private String detail;
    private String contact;
    private String tempoLocation;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private AdoptionStatus adoptionStatus;
    private ApproveStatus approveStatus;
    private AnimalDTO animalDTO;
    private List<ImageDTO> images;

    public static PostDTO fromPost(Post post) {
        List<ImageDTO> images = new ArrayList<>();
        if(post.getImages() != null){
            images = post.getImages().stream()
                    .map(ImageDTO::fromImage)
                    .collect(Collectors.toList());
        }

        return PostDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .fountAt(post.getFountAt())
                .fountLocation(post.getFountLocation())
                .detail(post.getDetail())
                .contact(post.getContact())
                .tempoLocation(post.getTempoLocation())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .adoptionStatus(post.getAdoptionStatus())
                .approveStatus(post.getApproveStatus())
                .animalDTO(AnimalDTO.fromAnimal(post.getAnimal()))
                .images(images)
                .build();
    }
}
