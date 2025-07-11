package com.shds.sma.api.service;

import com.shds.sma.api.dto.member.ApiMemberModRequestDto;
import com.shds.sma.api.dto.member.ApiMemberSaveRequestDto;
import com.shds.sma.api.dto.member.ApiMemberResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApiMemberService {
    Page<ApiMemberResponseDto> getAllMembers(Pageable pageable);

    ApiMemberResponseDto getMember(Long memberId);

    ApiMemberResponseDto createMember(ApiMemberSaveRequestDto apiMemberSaveRequestDto);

    ApiMemberResponseDto updateMember(ApiMemberModRequestDto apiMemberModRequestDto);

    void deleteMember(Long memberId);
}
