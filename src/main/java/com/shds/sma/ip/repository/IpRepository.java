package com.shds.sma.ip.repository;

import com.shds.sma.ip.entity.Ip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpRepository extends JpaRepository<Ip, Long>, IpQueryRepository {
}
