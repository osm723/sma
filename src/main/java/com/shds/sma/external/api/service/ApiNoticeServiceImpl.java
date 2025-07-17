package com.shds.sma.external.api.service;

import com.shds.sma.apps.admin.entity.Notice;
import com.shds.sma.apps.admin.repository.notice.NoticeRepository;
import com.shds.sma.external.api.dto.notice.ApiNoticeModRequestDto;
import com.shds.sma.external.api.dto.notice.ApiNoticeResponseDto;
import com.shds.sma.external.api.dto.notice.ApiNoticeSaveRequestDto;
import com.shds.sma.common.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.shds.sma.common.exception.ExceptionMessageConst.NOT_FOUND_NOTICE;

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
        Notice notice = getNoticeById(noticeId);
        return modelMapper.map(notice, ApiNoticeResponseDto.class);
    }

    @Override
    public ApiNoticeResponseDto createNotice(ApiNoticeSaveRequestDto apiMemberSaveRequestDto) {
        Notice createdNotice = noticeRepository.save(modelMapper.map(apiMemberSaveRequestDto, Notice.class));
        return modelMapper.map(createdNotice, ApiNoticeResponseDto.class);
    }

    @Override
    public ApiNoticeResponseDto updateNotice(ApiNoticeModRequestDto apiMemberModRequestDto) {
        Notice updatedNotice = getNoticeById(apiMemberModRequestDto.getNoticeId());
        updatedNotice.noticeModified(apiMemberModRequestDto);
        return modelMapper.map(updatedNotice, ApiNoticeResponseDto.class);
    }

    @Override
    public void deleteNotice(Long noticeId) {
        Notice deletedNotice = getNoticeById(noticeId);
        deletedNotice.setValidityN();
    }

    @Override
    public void reuseNotice(Long noticeId) {
        Notice deletedNotice = getNoticeById(noticeId);
        deletedNotice.setValidityY();
    }

    private Notice getNoticeById(Long noticeId) {
        return noticeRepository.findById(noticeId).orElseThrow(() -> new BizException(NOT_FOUND_NOTICE));
    }
}
