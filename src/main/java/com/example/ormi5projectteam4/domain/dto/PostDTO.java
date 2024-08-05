package com.example.ormi5projectteam4.domain.dto;

import com.example.ormi5projectteam4.domain.entity.Post;
import com.example.ormi5projectteam4.domain.constant.AdoptionStatus;
import com.example.ormi5projectteam4.domain.constant.ApproveStatus;
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
    private Long id;
    private String title;
    private LocalDate foundAt;
    private String foundLocation;
    private String detail;
    private String contact;
    private String tempoLocation;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private AdoptionStatus adoptionStatus;
    private ApproveStatus approveStatus;
    private AnimalDTO animalDTO;
//    private UserRoleDto userRoleDTO;
    private List<ImageDTO> images;

    public static PostDTO fromPost(Post post) {
        List<ImageDTO> images = new ArrayList<>();
        if(post.getImages() != null){
            images = post.getImages().stream()
                    .map(ImageDTO::fromImage)
                    .collect(Collectors.toList());
        }
        //user 추가
        return PostDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .foundAt(post.getFoundAt())
                .foundLocation(post.getFoundLocation())
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
