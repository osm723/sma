package com.shds.sma.admin.repositroy.member;

import com.shds.sma.admin.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryRepository {
}
