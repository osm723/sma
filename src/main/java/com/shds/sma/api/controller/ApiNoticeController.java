package com.shds.sma.api.controller;

import com.shds.sma.api.dto.notice.ApiNoticeModRequestDto;
import com.shds.sma.api.dto.notice.ApiNoticeResponseDto;
import com.shds.sma.api.dto.notice.ApiNoticeSaveRequestDto;
import com.shds.sma.api.service.ApiNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<>(notices, HttpStatus.OK);
    }

    @GetMapping("/notice/{noticeId}")
    public ResponseEntity<ApiNoticeResponseDto> getNotice(@PathVariable Long noticeId) {
        ApiNoticeResponseDto notice = apiNoticeService.getNotice(noticeId);
        return new ResponseEntity<>(notice, HttpStatus.OK);
    }

    @PostMapping("/notice")
    public  ResponseEntity<ApiNoticeResponseDto> createNotice(@RequestBody ApiNoticeSaveRequestDto apiNoticeSaveRequestDto) {
        ApiNoticeResponseDto createdNotice = apiNoticeService.createNotice(apiNoticeSaveRequestDto);
        return new ResponseEntity<>(createdNotice, HttpStatus.OK);
    }

    @PutMapping("/notice")
    public ResponseEntity<ApiNoticeResponseDto> updateNotice(@RequestBody ApiNoticeModRequestDto apiNoticeModRequestDto) {
        ApiNoticeResponseDto updatedNotice = apiNoticeService.updateNotice(apiNoticeModRequestDto);
        return new ResponseEntity<>(updatedNotice, HttpStatus.OK);
    }

    @DeleteMapping("/notice/{noticeId}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long noticeId) {
        apiNoticeService.deleteNotice(noticeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
