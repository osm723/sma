package com.shds.sma.admin.controller;

import com.shds.sma.admin.dto.*;
import com.shds.sma.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    /**
     * 관리자 메인화면
     * adminMain
     * @return String
     */
    @GetMapping("/main")
    public String adminMain() {
        return "/admin/adminMain";
    }

    /**
     * 공지사항 조회화면 (조건)
     * notice
     * @param pageable
     * @param model
     * @return String
     */
    @GetMapping("/notice")
    public String notice(NoticeCondRequestDto noticeCondRequestDto,Pageable pageable, Model model) {
        //Page<NoticeResponseDto> notices = adminService.findNoticeAll(pageable);
        Page<NoticeResponseDto> notices = adminService.findNoticeCond(noticeCondRequestDto, pageable);
        model.addAttribute("notices", notices);
        model.addAttribute("cond", noticeCondRequestDto);
        return "/admin/notice";
    }

    /**
     * 공지사항 조회화면 (조건)
     * noticeCond
     * @param pageable
     * @param model
     * @return String
     */
//    @GetMapping("/notice/cond")
//    public String noticeCond(NoticeCondRequestDto noticeCondRequestDto, Pageable pageable, Model model) {
//        Page<NoticeResponseDto> notices = adminService.findNoticeCond(noticeCondRequestDto, pageable);
//        model.addAttribute("noticesCond", notices);
//        model.addAttribute("cond", noticeCondRequestDto);
//        return "/admin/notice";
//    }

    /**
     * 공지사항 상세화면
     * noticeDetail
     * @param noticeId
     * @param model
     * @return String
     */
    @GetMapping("/noticeDetail")
    public String noticeDetail(Long noticeId, Model model) {
        NoticeResponseDto notice = adminService.findNoticeById(noticeId);
        model.addAttribute("notice", notice);
        return "/admin/noticeDetail";
    }

    /**
     * 공지사항 등록화면 폼
     * saveNoticeForm
     * @return String
     */
    @GetMapping("/notice/save")
    public String saveNoticeForm() {
        return "/admin/noticeSaveForm";
    }

    /**
     * 공지사항 저장
     * noticeSave
     * @param noticeSaveRequestDto
     * @return String
     */
    @PostMapping("/notice/save")
    public String noticeSave(NoticeSaveRequestDto noticeSaveRequestDto) {
        adminService.noticeSave(noticeSaveRequestDto);
        return "redirect:/admin/notice";
    }

    /**
     * 공지사항 수정
     * noticeModified
     * @param noticeModRequestDto
     * @return String
     */
    @PostMapping("/notice/modified")
    public String noticeModified(NoticeModRequestDto noticeModRequestDto) {
        adminService.noticeModified(noticeModRequestDto);
        return "redirect:/admin/noticeDetail?noticeId="+noticeModRequestDto.getNoticeId();
    }

    /**
     * 공지사항 삭제
     * noticeRemove
     * @param noticeId
     * @return ResponseEntity<String>
     */
    @PostMapping("/notice/remove")
    public ResponseEntity<String> noticeRemove(@RequestParam Long noticeId) {
        adminService.noticeRemove(noticeId);
        return ResponseEntity.ok("공지를 삭제 완료했습니다.");
    }

    /**
     * 공지사항 사용
     * noticeUse
     * @param noticeId
     * @return ResponseEntity<String>
     */
    @PostMapping("/notice/use")
    public ResponseEntity<String> noticeUse(@RequestParam Long noticeId) {
        adminService.noticeUse(noticeId);
        return ResponseEntity.ok("공지를 사용 완료했습니다.");
    }



}
