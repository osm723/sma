package com.shds.sma.home.controller;

import com.shds.sma.admin.dto.HomeNoticeResponseDto;
import com.shds.sma.admin.dto.NoticeResponseDto;
import com.shds.sma.home.service.HomeService;
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
        Page<HomeNoticeResponseDto> homeNotice = homeService.findHomeNotice(pageable);
        model.addAttribute("homeNotice", homeNotice);
        return "/home";
    }
}
