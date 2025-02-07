package com.shds.sma.manage.repository.cert;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shds.sma.manage.dto.cert.CertRequestDto;
import com.shds.sma.manage.entity.Cert;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

import static com.shds.sma.manage.entity.QCert.cert;
import static com.shds.sma.manage.entity.QIp.ip;

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

    private BooleanExpression certNameLike(String certName) {
        return StringUtils.hasText(certName) ? cert.certName.like("%"+certName+"%") : null;
    }
}
