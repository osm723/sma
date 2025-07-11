package com.shds.sma.cert.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shds.sma.cert.dto.CertRequestDto;
import com.shds.sma.cert.entity.Cert;
import com.shds.sma.system.dto.QSystemCertResponseDto;
import com.shds.sma.system.dto.SystemCertRequestDto;
import com.shds.sma.system.dto.SystemCertResponseDto;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

import static com.shds.sma.cert.entity.QCert.cert;

@Repository
public class CertQueryRepositoryImpl implements CertQueryRepository {

    private final JPAQueryFactory query;

    public CertQueryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Page<Cert> findCertByCond(CertRequestDto certRequestDto, Pageable pageable) {
        QueryResults<Cert> result = query.select(cert)
                .from(cert)
                .leftJoin(cert.approval).fetchJoin()
                .where(
                        certNameLike(certRequestDto.getCertName())
                        ,systemNameLike(certRequestDto.getApplySystemName())
                        ,memberNameLike(certRequestDto.getMemberName())
                        ,validityEq(certRequestDto.getValidity())
                        ,dateBetween(certRequestDto.getStartDate(), certRequestDto.getEndDate())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    @Override
    public Page<SystemCertResponseDto> findSystemCertByCond(SystemCertRequestDto systemCertRequestDto, Pageable pageable) {
        QueryResults<SystemCertResponseDto> result = query.select(
                new QSystemCertResponseDto(
                        cert.certName,
                        cert.applySystem.systemName)
                )
                .from(cert)
                .where(
                        certNameLike(systemCertRequestDto.getCertName())
                        ,systemIdEq(systemCertRequestDto.getSystemId())
                        ,validityEq(systemCertRequestDto.getValidity())
                        ,dateBetween(systemCertRequestDto.getStartDate(), systemCertRequestDto.getEndDate())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .groupBy(cert.certName, cert.applySystem.systemName)
                .fetchResults();
        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    @Override
    public List<Cert> findCertPreExpiration() {
        return query.select(cert)
                .from(cert)
                .where(cert.endDate.goe(LocalDate.now().minusDays(Long.parseLong(cert.applySystem.preCertAlarm.toString()))))
                .fetch();
    }

    @Override
    public boolean isCertReApply(Cert preCert) {
        BooleanExpression existsExpr = query.selectOne()
                .from(cert)
                .where(validityEq("Y")
                        , systemIdEq(preCert.getApplySystem().getId())
                        , memberIdEq(preCert.getMember().getId())
                        , certNameEq(preCert.getCertName())
                        , startDateGoeNow(preCert.getStartDate())
                        , endDateLtNow(preCert.getEndDate())
                )
                .exists();

        return Boolean.TRUE.equals(query.select(existsExpr).fetchOne());
    }

    @Override
    public List<Cert> findCertPreDayExpiration(Long preDay) {
        return query.select(cert)
                .from(cert)
                .where(cert.endDate.goe(LocalDate.now().minusDays(preDay)))
                .fetch();
    }

    private BooleanExpression dateBetween(LocalDate startDate, LocalDate endDate) {
        return startDate != null && endDate != null ?
                cert.startDate.between(startDate, endDate)
                        .and(cert.endDate.between(startDate, endDate)) : null;
    }

    private BooleanExpression validityEq(String validity) {
        return StringUtils.hasText(validity) ? cert.validity.eq(validity) : null;
    }

    private BooleanExpression memberNameLike(String applyMemberName) {
        return StringUtils.hasText(applyMemberName) ? cert.member.name.like("%"+applyMemberName+"%") : null;
    }

    private BooleanExpression systemNameLike(String applySystemName) {
        return StringUtils.hasText(applySystemName) ? cert.applySystem.systemName.like("%"+applySystemName+"%") : null;
    }

    private BooleanExpression systemIdEq(Long applySystemId) {
        return applySystemId != null ? cert.applySystem.id.eq(applySystemId) : null;
    }

    private BooleanExpression certNameLike(String certName) {
        return StringUtils.hasText(certName) ? cert.certName.like("%"+certName+"%") : null;
    }

    private BooleanExpression memberIdEq(Long applyMemberId) {
        return applyMemberId != null ? cert.member.id.eq(applyMemberId) : null;
    }

    private BooleanExpression certNameEq(String certName) {
        return StringUtils.hasText(certName) ? cert.certName.eq(certName) : null;
    }

    private BooleanExpression startDateGoeNow(LocalDate startDate) {
        return startDate != null ? cert.startDate.goe(LocalDate.now()) : null;
    }

    private BooleanExpression endDateLtNow(LocalDate endDate) {
        return endDate != null ? cert.endDate.lt(LocalDate.now()) : null;
    }
}
