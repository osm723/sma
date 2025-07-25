package com.shds.sma.apps.admin.member.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shds.sma.apps.admin.member.dto.MemberRequestDto;
import com.shds.sma.apps.admin.member.entity.Member;
import com.shds.sma.apps.system.dto.SystemManagerRequestDto;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.shds.sma.apps.admin.client.entity.QClient.client;
import static com.shds.sma.apps.admin.member.entity.QMember.member;
import static com.shds.sma.apps.system.entity.QSystem.system;


@Repository
public class MemberQueryRepositoryImpl implements MemberQueryRepository {

    private final JPAQueryFactory query;

    public MemberQueryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Page<Member> findMemberByCond(MemberRequestDto memberRequestDto, Pageable pageable) {
        QueryResults<Member> result = query.select(member)
                .from(member)
                .join(member.client, client).fetchJoin()
                .join(member.system, system).fetchJoin()
                .where(nameEq(memberRequestDto.getName())
//                        ,clientNameEq(memberRequestDto.getClient().getClientName())
                        ,deptNameNameEq(memberRequestDto.getDeptName())
                        ,gradeNameEq(memberRequestDto.getGradeName())
                        ,roleNameEq(memberRequestDto.getRoleName())
                )
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();

        List<Member> content = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<Member> findSystemMemberByCond(SystemManagerRequestDto systemManagerRequestDto, Pageable pageable) {
        QueryResults<Member> result = query.select(member)
                .from(member)
                .join(member.client, client).fetchJoin()
                .join(member.system, system).fetchJoin()
                .where(nameEq(systemManagerRequestDto.getName())
//                        ,clientNameEq(memberRequestDto.getClient().getClientName())
                        ,deptNameNameEq(systemManagerRequestDto.getDeptName())
                        ,gradeNameEq(systemManagerRequestDto.getGradeName())
                        ,roleNameEq(systemManagerRequestDto.getRoleName())
                        ,systemIdEq(systemManagerRequestDto.getSystemId())
                )
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();

        List<Member> content = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private static BooleanExpression systemIdEq(Long systemId) {
        return systemId != null ? member.system.id.eq(systemId) : null;
    }

    private static BooleanExpression nameEq(String name) {
        return StringUtils.hasText(name) ? member.name.eq(name) : null;
    }

    private static BooleanExpression clientNameEq(String clientName) {
        return StringUtils.hasText(clientName) ? member.client.clientName.eq(clientName) : null;
    }

    private static BooleanExpression systemNameNameEq(String systemName) {
        return StringUtils.hasText(systemName) ? member.system.systemName.eq(systemName) : null;
    }

    private static BooleanExpression deptNameNameEq(String deptName) {
        return StringUtils.hasText(deptName) ? member.deptName.eq(deptName) : null;
    }

    private static BooleanExpression gradeNameEq(String gradeName) {
        return StringUtils.hasText(gradeName) ? member.gradeName.eq(gradeName) : null;
    }

    private static BooleanExpression roleNameEq(String roleName) {
        return StringUtils.hasText(roleName) ? member.roleName.eq(roleName) : null;
    }


}
