package com.shds.sma.apps.admin.repository.member;

import com.shds.sma.apps.admin.dto.member.MemberRequestDto;
import com.shds.sma.apps.admin.entity.Member;
import com.shds.sma.apps.system.dto.SystemManagerRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberQueryRepository {

    Page<Member> findMemberByCond(MemberRequestDto memberRequestDto, Pageable pageable);

    Page<Member> findSystemMemberByCond(SystemManagerRequestDto systemManagerRequestDto, Pageable pageable);
}
