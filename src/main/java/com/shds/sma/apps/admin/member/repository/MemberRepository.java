package com.shds.sma.apps.admin.member.repository;

import com.shds.sma.apps.admin.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryRepository {
}
