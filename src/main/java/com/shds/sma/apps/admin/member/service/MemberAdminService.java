package com.shds.sma.apps.admin.member.service;

import com.shds.sma.apps.admin.member.dto.MemberModRequestDto;
import com.shds.sma.apps.admin.member.dto.MemberRequestDto;
import com.shds.sma.apps.admin.member.dto.MemberResponseDto;
import com.shds.sma.apps.admin.member.dto.MemberSaveRequestDto;
import com.shds.sma.apps.admin.member.entity.type.EmpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberAdminService {

    List<MemberResponseDto> findMemberAll();

    Page<MemberResponseDto> findMemberByCond(MemberRequestDto memberRequestDto, Pageable pageable);

    MemberResponseDto findMemberById(Long memberId);

    void saveMember(MemberSaveRequestDto memberSaveRequestDto);

    void modifiedMember(MemberModRequestDto memberModRequestDto);

    void memberChangeStatus(Long memberId, EmpStatus empStatus);
}
