package com.shds.sma.admin.repositroy.member;

import com.shds.sma.admin.dto.member.MemberRequestDto;
import com.shds.sma.admin.entity.Member;
import com.shds.sma.system.dto.SystemManagerRequestDto;
import com.shds.sma.system.dto.SystemManagerResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberQueryRepository {

    Page<Member> findMemberByCond(MemberRequestDto memberRequestDto, Pageable pageable);

    Page<Member> findSystemMemberByCond(SystemManagerRequestDto systemManagerRequestDto, Pageable pageable);
}
