package com.example.ormi5projectteam4.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagedPostsResponse {
    private List<PostDTO> content;
    private int number;
    private int totalPages;
    private long totalElements;
}
