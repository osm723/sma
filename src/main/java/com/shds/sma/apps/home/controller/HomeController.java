package com.shds.sma.apps.home.controller;

import com.shds.sma.apps.admin.notice.dto.NoticeHomeResponseDto;
import com.shds.sma.apps.home.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/")
    public String home(Model model, Pageable pageable) {
        Page<NoticeHomeResponseDto> homeNotice = homeService.findHomeNotice(pageable);
        model.addAttribute("homeNotice", homeNotice);
        return "/home";
    }
}
