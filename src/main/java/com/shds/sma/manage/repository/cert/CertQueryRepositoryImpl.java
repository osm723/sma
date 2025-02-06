package com.shds.sma.manage.repository.cert;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shds.sma.manage.dto.cert.CertRequestDto;
import com.shds.sma.manage.entity.Cert;
import com.shds.sma.manage.entity.QCert;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.shds.sma.manage.entity.QCert.cert;

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
//                .where()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }
}
