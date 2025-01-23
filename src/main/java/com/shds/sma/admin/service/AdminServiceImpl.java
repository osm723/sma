package com.shds.sma.admin.service;

import com.shds.sma.admin.dto.*;
import com.shds.sma.admin.entity.Notice;
import com.shds.sma.admin.repositroy.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final NoticeRepository noticeRepository;

    private final ModelMapper modelMapper;

    @Override
    public Page<NoticeResponseDto> findNoticeAll(Pageable pageable) {
        Page<Notice> notices = noticeRepository.findAll(pageable);
        return notices.map(NoticeResponseDto::new);
    }

    @Override
    public Page<NoticeResponseDto> findNoticeCond(NoticeCondRequestDto noticeCondRequestDto, Pageable pageable) {
        Page<Notice> notices = noticeRepository.findNoticeCond(noticeCondRequestDto, pageable);
        return notices.map(NoticeResponseDto::new);
    }

    @Override
    public Page<HomeNoticeResponseDto> findHomeNotice(Pageable pageable) {
        Page<Notice> notices = noticeRepository.findHomeNotice(pageable);
        return notices.map(HomeNoticeResponseDto::new);
    }

    @Override
    public NoticeResponseDto findNoticeById(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId).get();
        return modelMapper.map(notice, NoticeResponseDto.class);
    }

    @Override
    public void noticeSave(NoticeSaveRequestDto noticeSaveRequestDto) {
        noticeRepository.save(modelMapper.map(noticeSaveRequestDto, Notice.class));
    }

    @Override
    public void noticeModified(NoticeModRequestDto noticeModRequestDto) {
        log.info("noticeRemoveRequestDto={}", noticeModRequestDto.getNoticeId());
        Notice findNotice = noticeRepository.findById(noticeModRequestDto.getNoticeId()).get();
        findNotice.noticeModified(noticeModRequestDto);
    }

    @Override
    public void noticeRemove(Long noticeId) {
        Notice findNotice = noticeRepository.findById(noticeId).get();
        findNotice.setValidityN();
    }

    @Override
    public void noticeUse(Long noticeId) {
        Notice findNotice = noticeRepository.findById(noticeId).get();
        findNotice.setValidityY();
    }
}
