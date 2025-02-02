package com.shds.sma.admin.repositroy.member;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shds.sma.admin.dto.member.MemberRequestDto;
import com.shds.sma.admin.entity.Member;
import com.shds.sma.admin.entity.QMember;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.shds.sma.admin.entity.QMember.member;


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
                .where(nameEq(memberRequestDto.getName())
                        ,clientNameEq(memberRequestDto.getClient().getClientName())
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

    private static BooleanExpression nameEq(String name) {
        return StringUtils.hasText(name) ? member.name.eq(name) : null;
    }

    private static BooleanExpression clientNameEq(String clientName) {
        return StringUtils.hasText(clientName) ? member.client.clientName.eq(clientName) : null;
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
