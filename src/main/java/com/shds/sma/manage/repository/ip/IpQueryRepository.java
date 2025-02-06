package com.shds.sma.manage.repository.ip;

import com.shds.sma.manage.dto.ip.IpRequestDto;
import com.shds.sma.manage.entity.Ip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IpQueryRepository {
    Page<Ip> findIpByCond(IpRequestDto ipRequestDto, Pageable pageable);
}
