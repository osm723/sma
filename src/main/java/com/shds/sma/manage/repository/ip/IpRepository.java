package com.shds.sma.manage.repository.ip;

import com.shds.sma.manage.entity.Ip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpRepository extends JpaRepository<Ip, Long>, IpQueryRepository {
}
