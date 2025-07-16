package com.shds.sma.apps.admin.repository.member;

import com.shds.sma.apps.admin.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryRepository {
}
