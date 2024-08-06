package com.example.ormi5projectteam4.controller.thymeleaf_controller;

import com.example.ormi5projectteam4.domain.dto.NoticeDto;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class WebController {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "http://localhost:8080";

    @GetMapping("/notice-list")
    public String getNotices(Model model) {
        ResponseEntity<List<NoticeDto>> response = restTemplate.exchange(
                BASE_URL + "/notice",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        List<NoticeDto> noticePage = response.getBody();
        model.addAttribute("notices", noticePage);
        return "notice-list-user";
    }

    @GetMapping("/manage/notice")
    public String getAdminNotices(Model model) {
        //ResponseEntity<List<NoticeDto>> response = restTemplate.exchange(
        //        BASE_URL + "/admin/notice",
        //        HttpMethod.GET,
        //        null,
        //        new ParameterizedTypeReference<>() {}
        //);
        //List<NoticeDto> noticePage = response.getBody();
        //model.addAttribute("notices", noticePage);
        return "notice-list-admin";
    }

    @GetMapping("/notice/{id}")
    public String getNoticeDetail(@PathVariable Long id, Model model) {
        String url = BASE_URL + "/notice/" + id;
        ResponseEntity<NoticeDto> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                NoticeDto.class
        );
        model.addAttribute("notice", response.getBody());
        return "notice-detail";
    }

    @GetMapping("/write-notice")
    public String getNoticeForm(Model model) {
        model.addAttribute("noticeDto", new NoticeDto());
        return "write-notice";
    }

    @PostMapping("/write-notice")
    public String submitNotice(NoticeDto noticeDto, Model model) {
        if (noticeDto.getTitle() == null || noticeDto.getTitle().isEmpty()) {
            model.addAttribute("titleError", "제목을 입력해주세요");
            return "write-notice";
        }
        if (noticeDto.getContent() == null || noticeDto.getContent().isEmpty()) {
            model.addAttribute("contentError", "내용을 입력해주세요");
            return "write-notice";
        }

        restTemplate.postForObject(BASE_URL + "/notice", noticeDto, NoticeDto.class);
        return "redirect:/manage/notice";
    }




//    @GetMapping("/notice/{id}")
//    public String getNoticeDetail(@PathVariable Long id, Model model) {
//        String url = BASE_URL + "/" + id;
//        ResponseEntity<NoticeDto> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                null,
//                NoticeDto.class
//        );
//        NoticeDto notice = response.getBody();
//
//        String recentNoticesUrl = BASE_URL + "/recent";
//        ResponseEntity<List<NoticeDto>> recentNoticesResponse = restTemplate.exchange(
//                recentNoticesUrl,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<>() {}
//        );
//
//        List<NoticeDto> recentNotices = recentNoticesResponse.getBody();
//
//        model.addAttribute("notice", notice);
//        model.addAttribute("recentNotices", recentNotices);
//
//        return "notice-detail";
//    }

}