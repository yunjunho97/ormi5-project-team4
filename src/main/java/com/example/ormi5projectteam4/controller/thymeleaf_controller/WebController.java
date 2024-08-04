package com.example.ormi5projectteam4.controller.thymeleaf_controller;

import com.example.ormi5projectteam4.domain.dto.NoticeDto;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class WebController {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "http://localhost:8080";

    @GetMapping("/notice")
    public String getNotices(@RequestParam(defaultValue = "1") int page, Model model) {
        String url = BASE_URL + "?page=" + page;
        ResponseEntity<List<NoticeDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        List<NoticeDto> noticePage = response.getBody();

        model.addAttribute("notices", noticePage);
        model.addAttribute("recentNotices", Objects.requireNonNull(noticePage).subList(0, 5));

        return "notice-list-user";
    }

    @GetMapping("/manage/notice")
    public String getAdminNotices(Model model) {
        String url = BASE_URL;
        ResponseEntity<List<NoticeDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        List<NoticeDto> noticePage = response.getBody();

        model.addAttribute("notices", noticePage);
        model.addAttribute("page", noticePage);

        return "notice-list-admin";
    }

    @GetMapping("/notice/{id}")
    public String getNotices(@PathVariable Long id, Model model) {
        String url = BASE_URL + "/" + id;
        ResponseEntity<NoticeDto> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                NoticeDto.class
        );
        NoticeDto notice = response.getBody();

        // Optional<NoticeDto> notice = Optional.ofNullable(response.getBody());
        String recentNoticesUrl = BASE_URL + "/recent";
        ResponseEntity<List<NoticeDto>> recentNoticesResponse = restTemplate.exchange(
                recentNoticesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        List<NoticeDto> recentNotices = recentNoticesResponse.getBody();

        model.addAttribute("notice", notice);
        model.addAttribute("recentNotices", recentNotices);

        return "notice-detail";
    }

}