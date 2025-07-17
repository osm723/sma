package com.shds.sma.apps.admin.notice.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shds.sma.apps.admin.notice.dto.NoticeCondRequestDto;
import com.shds.sma.apps.admin.notice.entity.Notice;
import com.shds.sma.apps.admin.notice.entity.type.NoticeType;
import com.shds.sma.common.types.ValidityStatus;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

import static com.shds.sma.apps.admin.notice.entity.QNotice.notice;
import static com.shds.sma.common.constants.Constants.VALIDITY_Y;

@Repository
public class NoticeQueryRepositoryImpl implements NoticeQueryRepository {

    private JPAQueryFactory query;

    public NoticeQueryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Page<Notice> findNoticeByCond(NoticeCondRequestDto noticeCondRequestDto, Pageable pageable) {
        QueryResults<Notice> result = query.select(notice)
                .from(notice)
                .where(subjectEq(noticeCondRequestDto.getSubject())
                        ,(validityEq(noticeCondRequestDto.getValidity()))
                        ,(dateBetween(noticeCondRequestDto.getStartDate(), noticeCondRequestDto.getEndDate()))
                        ,(noticeTypeIn(noticeCondRequestDto.getNoticeType()))
                )
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();

        List<Notice> content = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<Notice> findHomeNotice(Pageable pageable) {
        QueryResults<Notice> result = query.select(notice)
                .from(notice)
                .where(validityEq(VALIDITY_Y)
                        ,notice.startDate.loe(LocalDate.now())
                        ,notice.endDate.goe(LocalDate.now())
                )
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(notice.noticeType.asc())
                .fetchResults();

        List<Notice> content = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression subjectEq(String subject) {
        return StringUtils.hasText(subject) ? notice.subject.eq(subject) : null;
    }

    private BooleanExpression validityEq(String validity) {
        return StringUtils.hasText(validity) ? notice.validity.eq(ValidityStatus.valueOf(validity)) : null;
    }

    private BooleanExpression noticeTypeIn(List<String> noticeType) {
        if (noticeType == null || noticeType.isEmpty()) {
            return null;
        }

        List<NoticeType> enumValues = noticeType.stream()
                .map(NoticeType::valueOf)
                .toList();

        return notice.noticeType.in(enumValues);
    }

    private BooleanExpression dateBetween(LocalDate startDate, LocalDate endDate) {
        return startDate != null && endDate != null ?
                        notice.startDate.between(startDate, endDate)
                        .and(notice.endDate.between(startDate, endDate)) : null;
    }
}

