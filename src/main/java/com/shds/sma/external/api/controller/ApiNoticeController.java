package com.shds.sma.external.api.controller;

import com.shds.sma.external.api.dto.notice.ApiNoticeModRequestDto;
import com.shds.sma.external.api.dto.notice.ApiNoticeResponseDto;
import com.shds.sma.external.api.dto.notice.ApiNoticeSaveRequestDto;
import com.shds.sma.external.api.service.ApiNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ApiNoticeController {

    private final ApiNoticeService apiNoticeService;

    @GetMapping("/notices")
    public ResponseEntity<Page<ApiNoticeResponseDto>> getAllNotices(Pageable pageable) {
        Page<ApiNoticeResponseDto> notices = apiNoticeService.getAllNotices(pageable);
        return ResponseEntity.ok(notices);
    }

    @GetMapping("/notice/{noticeId}")
    public ResponseEntity<ApiNoticeResponseDto> getNotice(@PathVariable Long noticeId) {
        ApiNoticeResponseDto notice = apiNoticeService.getNotice(noticeId);
        return ResponseEntity.ok(notice);
    }

    @PostMapping("/notice")
    public ResponseEntity<ApiNoticeResponseDto> createNotice(@RequestBody ApiNoticeSaveRequestDto apiNoticeSaveRequestDto) {
        ApiNoticeResponseDto createdNotice = apiNoticeService.createNotice(apiNoticeSaveRequestDto);
        return ResponseEntity.ok(createdNotice);
    }

    @PutMapping("/notice")
    public ResponseEntity<ApiNoticeResponseDto> updateNotice(@RequestBody ApiNoticeModRequestDto apiNoticeModRequestDto) {
        ApiNoticeResponseDto updatedNotice = apiNoticeService.updateNotice(apiNoticeModRequestDto);
        return ResponseEntity.ok(updatedNotice);
    }

    @DeleteMapping("/notice/{noticeId}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long noticeId) {
        apiNoticeService.deleteNotice(noticeId);
        return ResponseEntity.ok().build();
    }
}
