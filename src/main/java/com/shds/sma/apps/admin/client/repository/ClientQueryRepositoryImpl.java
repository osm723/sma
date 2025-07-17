package com.shds.sma.apps.admin.client.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shds.sma.apps.admin.client.dto.ClientRequestDto;
import com.shds.sma.apps.admin.client.entity.Client;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.shds.sma.apps.admin.client.entity.QClient.client;

@Repository
public class ClientQueryRepositoryImpl implements ClientQueryRepository {

    private final JPAQueryFactory query;

    public ClientQueryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Page<Client> findClientByCond(ClientRequestDto clientRequestDto, Pageable pageable) {
        QueryResults<Client> result = query.select(client)
                .from(client)
                .where(clientCodeEq(clientRequestDto.getClientCode())
                        , clientNameLike(clientRequestDto.getClientName())
                )
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();

        List<Client> content = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private static BooleanExpression clientCodeEq(String clientCode) {
        if (!StringUtils.hasText(clientCode)) {
            return null;
        }

        return injectionCheck(client.clientCode, clientCode);
    }

    private static BooleanExpression clientNameLike(String clientName) {
        if (!StringUtils.hasText(clientName)) {
            return null;
        }

        return injectionCheck(client.clientName, clientName);
    }

    /**
     * SQL Injection 패턴 검증
     * @param inputValue
     * @return
     */
    private static BooleanExpression injectionCheck(StringPath dataColumn, String inputValue) {
        String sanitized = inputValue.replaceAll("[';\"\\-\\-]", "");
        return dataColumn.like("%" + sanitized + "%");
    }
}


