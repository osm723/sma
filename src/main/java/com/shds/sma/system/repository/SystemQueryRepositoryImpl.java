package com.shds.sma.system.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shds.sma.system.dto.SystemRequestDto;
import com.shds.sma.system.entity.System;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.shds.sma.system.entity.QSystem.system;


@Repository
public class SystemQueryRepositoryImpl implements SystemQueryRepository {

    private JPAQueryFactory query;

    public SystemQueryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Page<System> findSystemByCond(SystemRequestDto systemRequestDto, Pageable pageable) {
        QueryResults<System> result = query.select(system)
                .from(system)
                .leftJoin(system.systemManagers).fetchJoin()
                .where(systemNameEq(systemRequestDto.getSystemName())
                        ,(validityEq(systemRequestDto.getValidity()))
                )
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();

        List<System> content = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression systemNameEq(String systemName) {
        return StringUtils.hasText(systemName) ? system.systemName.eq(systemName) : null;
    }

    private BooleanExpression validityEq(String validity) {
        return StringUtils.hasText(validity) ? system.validity.eq(validity) : null;
    }

}

