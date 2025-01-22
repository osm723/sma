package com.shds.sma.admin.controller;

import com.shds.sma.admin.dto.NoticeModRequestDto;
import com.shds.sma.admin.dto.NoticeRemoveRequestDto;
import com.shds.sma.admin.dto.NoticeResponseDto;
import com.shds.sma.admin.dto.NoticeSaveRequestDto;
import com.shds.sma.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/main")
    public String adminMain() {
        return "/admin/adminMain";
    }

    @GetMapping("/notice")
    public String notice(Pageable pageable, Model model) {
        Page<NoticeResponseDto> notices = adminService.findNoticeAll(pageable);
        model.addAttribute("notices", notices);
        for (NoticeResponseDto notice : notices) {
            System.out.println("notice = " + notice.getId());
        }
        return "/admin/notice";
    }

    @GetMapping("/notice/cond")
    public String noticeCond() {
        adminService.findNoticeCond();
        return "/admin/notice";
    }

    @GetMapping("/noticeDetail")
    public String noticeDetail(Long noticeId, Model model) {
        NoticeResponseDto notice = adminService.findNoticeById(noticeId);
        model.addAttribute("notice", notice);
        return "/admin/noticeDetail";
    }


    @GetMapping("/notice/save")
    public String saveNoticeForm() {
        return "/admin/noticeSaveForm";
    }

    @PostMapping("/notice/save")
    public String noticeSave(NoticeSaveRequestDto noticeSaveRequestDto) {
        adminService.noticeSave(noticeSaveRequestDto);
        return "redirect:/admin/notice";
    }

    @PostMapping("/notice/modified")
    public String noticeModified(NoticeModRequestDto noticeModRequestDto) {
        adminService.noticeModified(noticeModRequestDto);
        return "/admin/notice";
    }

    @PostMapping("/notice/remove")
    public String noticeRemove(NoticeRemoveRequestDto noticeRemoveRequestDto) {
        adminService.noticeRemove(noticeRemoveRequestDto);
        return "/admin/notice";
    }



}
