package com.shds.sma.ip.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shds.sma.ip.dto.IpRequestDto;
import com.shds.sma.ip.entity.Ip;
import com.shds.sma.system.dto.QSystemIpResponseDto;
import com.shds.sma.system.dto.SystemIpRequestDto;
import com.shds.sma.system.dto.SystemIpResponseDto;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

import static com.shds.sma.ip.entity.QIp.ip;

@Repository
public class IpQueryRepositoryImpl implements IpQueryRepository {

    private final JPAQueryFactory query;

    public IpQueryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Page<Ip> findIpByCond(IpRequestDto ipRequestDto, Pageable pageable) {
        QueryResults<Ip> result = query.select(ip)
                .from(ip)
                .where(
                        startIpAddrLike(ipRequestDto.getStartIpAddr())
                        ,endIpAddrLike(ipRequestDto.getEndIpAddr())
                        ,systemNameLike(ipRequestDto.getApplySystemName())
                        ,memberNameLike(ipRequestDto.getMemberName())
                        ,validityEq(ipRequestDto.getValidity())
                        ,dateBetween(ipRequestDto.getStartDate(), ipRequestDto.getEndDate())
                )
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    @Override
    public Page<SystemIpResponseDto> findSystemIpByCond(SystemIpRequestDto systemIpRequestDto, Pageable pageable) {
        QueryResults<SystemIpResponseDto> result = query.select(new QSystemIpResponseDto(
                        ip.startIpAddr,
                        ip.endIpAddr,
                        ip.applySystem.systemName))
                .from(ip)
                .where(
                        startIpAddrLike(systemIpRequestDto.getStartIpAddr())
                        , endIpAddrLike(systemIpRequestDto.getEndIpAddr())
                        , systemIdEq(systemIpRequestDto.getSystemId())
                        , validityEq(systemIpRequestDto.getValidity())
                        , dateBetween(systemIpRequestDto.getStartDate(), systemIpRequestDto.getEndDate())
                )
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .groupBy(ip.startIpAddr, ip.endIpAddr, ip.applySystem)
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    private BooleanExpression dateBetween(LocalDate startDate, LocalDate endDate) {
        return startDate != null && endDate != null ?
                ip.startDate.between(startDate, endDate)
                        .and(ip.endDate.between(startDate, endDate)) : null;
    }

    private BooleanExpression validityEq(String validity) {
        return StringUtils.hasText(validity) ? ip.validity.eq(validity) : null;
    }

    private BooleanExpression memberNameLike(String applyMemberName) {
        return StringUtils.hasText(applyMemberName) ? ip.member.name.like("%"+applyMemberName+"%") : null;
    }

    private BooleanExpression systemNameLike(String applySystem) {
        return StringUtils.hasText(applySystem) ? ip.applySystem.systemName.like("%"+applySystem+"%") : null;
    }

    private BooleanExpression systemIdEq(Long applySystemId) {
        return applySystemId != null ? ip.applySystem.id.eq(applySystemId) : null;
    }

    private BooleanExpression startIpAddrLike(String startIpAddr) {
        return StringUtils.hasText(startIpAddr) ? ip.startIpAddr.like("%"+startIpAddr+"%") : null;
    }

    private BooleanExpression endIpAddrLike(String endIpAddr) {
        return StringUtils.hasText(endIpAddr) ? ip.endIpAddr.like(endIpAddr) : null;
    }


}
