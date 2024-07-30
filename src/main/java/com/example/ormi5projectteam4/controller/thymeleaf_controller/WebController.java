package com.example.ormi5projectteam4.controller.thymeleaf_controller;

import com.example.ormi5projectteam4.domain.dto.NoticeDto;
import com.example.ormi5projectteam4.service.NoticeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebController {

    private final NoticeService noticeService;

    public WebController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notice-list")
    public String getNotices(@RequestParam(defaultValue = "1") int page, Model model) {
        List<NoticeDto> noticePage = noticeService.getAllNotices();
        List<NoticeDto> recentNotices = noticeService.getLatestNotices();

        model.addAttribute("notices", noticePage);
        model.addAttribute("recentNotices", recentNotices);
        model.addAttribute("page", noticePage);

        return "notice-list-user";
    }

    @GetMapping("/admin/notice")
    public String getAdminNotices(Model model) {
        List<NoticeDto> noticePage = noticeService.getAllNotices();

        model.addAttribute("notices", noticePage);
        model.addAttribute("page", noticePage);

        return "notice-list-admin";
    }
}