package com.shds.sma.api.service;

import com.shds.sma.admin.entity.Notice;
import com.shds.sma.admin.repository.notice.NoticeRepository;
import com.shds.sma.api.dto.notice.ApiNoticeModRequestDto;
import com.shds.sma.api.dto.notice.ApiNoticeResponseDto;
import com.shds.sma.api.dto.notice.ApiNoticeSaveRequestDto;
import com.shds.sma.common.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ApiNoticeServiceImpl implements ApiNoticeService {

    private final NoticeRepository noticeRepository;

    private final ModelMapper modelMapper;

    @Override
    public Page<ApiNoticeResponseDto> getAllNotices(Pageable pageable) {
        Page<Notice> notices = noticeRepository.findAll(pageable);
        return notices.map(ApiNoticeResponseDto::new);
    }

    @Override
    public ApiNoticeResponseDto getNotice(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new BizException("존재하지 않는 공지입니다."));
        return modelMapper.map(notice, ApiNoticeResponseDto.class);
    }

    @Override
    public ApiNoticeResponseDto createNotice(ApiNoticeSaveRequestDto apiMemberSaveRequestDto) {
        Notice createdNotice = noticeRepository.save(modelMapper.map(apiMemberSaveRequestDto, Notice.class));
        return modelMapper.map(createdNotice, ApiNoticeResponseDto.class);
    }

    @Override
    public ApiNoticeResponseDto updateNotice(ApiNoticeModRequestDto apiMemberModRequestDto) {
        Notice updatedNotice = noticeRepository.findById(apiMemberModRequestDto.getNoticeId()).orElseThrow(() -> new BizException("존재하지 않는 공지입니다."));
        updatedNotice.noticeModified(apiMemberModRequestDto);
        return modelMapper.map(updatedNotice, ApiNoticeResponseDto.class);
    }

    @Override
    public void deleteNotice(Long noticeId) {
        Notice deletedNotice = noticeRepository.findById(noticeId).orElseThrow(() -> new BizException("존재하지 않는 공지입니다."));
        deletedNotice.setValidityN();
    }

    @Override
    public void reuseNotice(Long noticeId) {
        Notice deletedNotice = noticeRepository.findById(noticeId).orElseThrow(() -> new BizException("존재하지 않는 공지입니다."));
        deletedNotice.setValidityY();
    }
}
