package com.shds.sma.ip.repository;

import com.shds.sma.ip.dto.IpRequestDto;
import com.shds.sma.ip.entity.Ip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IpQueryRepository {
    Page<Ip> findIpByCond(IpRequestDto ipRequestDto, Pageable pageable);
}
