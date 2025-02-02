package com.shds.sma.admin.repositroy.client;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shds.sma.admin.dto.client.ClientRequestDto;
import com.shds.sma.admin.entity.Client;
import com.shds.sma.admin.entity.Notice;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.shds.sma.admin.entity.QClient.client;

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
        return StringUtils.hasText(clientCode) ? client.clientCode.eq(clientCode) : null;
    }

    private static BooleanExpression clientNameLike(String clientName) {
        return StringUtils.hasText(clientName) ? client.clientName.like(clientName) : null;
    }
}


