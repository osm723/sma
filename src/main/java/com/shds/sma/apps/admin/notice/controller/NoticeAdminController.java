package com.shds.sma.apps.admin.notice.controller;

import com.shds.sma.apps.admin.notice.dto.NoticeCondRequestDto;
import com.shds.sma.apps.admin.notice.dto.NoticeModRequestDto;
import com.shds.sma.apps.admin.notice.dto.NoticeResponseDto;
import com.shds.sma.apps.admin.notice.dto.NoticeSaveRequestDto;
import com.shds.sma.apps.admin.notice.service.NoticeAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.shds.sma.common.constants.Constants.NOTICE_DELETE_SUCCESS;
import static com.shds.sma.common.constants.Constants.NOTICE_USE_SUCCESS;
import static com.shds.sma.common.constants.Constants.UrlPath.ADMIN_NOTICE_URL;

@Controller
@RequestMapping(ADMIN_NOTICE_URL)
@RequiredArgsConstructor
public class NoticeAdminController {

    private final NoticeAdminService noticeAdminService;

    /**
     * 공지사항 조회화면 (조건)
     * notice
     * @param pageable
     * @param model
     * @return String
     */
    @GetMapping
    public String notice(NoticeCondRequestDto noticeCondRequestDto, Pageable pageable, Model model) {
        Page<NoticeResponseDto> notices = noticeAdminService.findNoticeByCond(noticeCondRequestDto, pageable);
        model.addAttribute("notices", notices);
        model.addAttribute("cond", noticeCondRequestDto);
        return "/admin/notice/noticeMain";
    }

    /**
     * 공지사항 상세화면
     * noticeDetail
     * @param noticeId
     * @param model
     * @return String
     */
    @GetMapping("/detail")
    public String noticeDetail(Long noticeId, Model model) {
        NoticeResponseDto notice = noticeAdminService.findNoticeById(noticeId);
        model.addAttribute("notice", notice);
        return "/admin/notice/noticeDetail";
    }

    /**
     * 공지사항 등록화면 폼
     * noticeSaveForm
     * @return String
     */
    @GetMapping("/save")
    public String noticeSaveForm() {
        return "/admin/notice/noticeSaveForm";
    }

    /**
     * 공지사항 저장
     * noticeSave
     * @param noticeSaveRequestDto
     * @return String
     */
    @PostMapping("/save")
    public String noticeSave(NoticeSaveRequestDto noticeSaveRequestDto) {
        noticeAdminService.saveNotice(noticeSaveRequestDto);
        return "redirect:/admin/notice";
    }

    /**
     * 공지사항 수정
     * modifiedNotice
     * @param noticeModRequestDto
     * @return String
     */
    @PostMapping("/modified")
    public String noticeModified(NoticeModRequestDto noticeModRequestDto) {
        noticeAdminService.modifiedNotice(noticeModRequestDto);
        return "redirect:/admin/notice/detail?noticeId="+noticeModRequestDto.getNoticeId();
    }

    /**
     * 공지사항 삭제
     * noticeRemove
     * @param noticeId
     * @return ResponseEntity<String>
     */
    @PostMapping("/remove")
    public ResponseEntity<String> noticeRemove(@RequestParam Long noticeId) {
        noticeAdminService.removeNotice(noticeId);
        return ResponseEntity.ok(NOTICE_DELETE_SUCCESS);
    }

    /**
     * 공지사항 사용
     * noticeUse
     * @param noticeId
     * @return ResponseEntity<String>
     */
    @PostMapping("/use")
    public ResponseEntity<String> noticeUse(@RequestParam Long noticeId) {
        noticeAdminService.useNotice(noticeId);
        return ResponseEntity.ok(NOTICE_USE_SUCCESS);
    }
}
