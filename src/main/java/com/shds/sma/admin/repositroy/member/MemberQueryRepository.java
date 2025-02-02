package com.shds.sma.admin.repositroy.member;

import com.shds.sma.admin.dto.member.MemberRequestDto;
import com.shds.sma.admin.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberQueryRepository {

    Page<Member> findMemberByCond(MemberRequestDto memberRequestDto, Pageable pageable);
}
