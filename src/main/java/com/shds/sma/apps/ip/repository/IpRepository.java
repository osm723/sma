package com.shds.sma.apps.ip.repository;

import com.shds.sma.apps.ip.entity.Ip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface IpRepository extends JpaRepository<Ip, Long>, IpQueryRepository {

    Page<Ip> findByEndDateBefore(LocalDate currentDate, Pageable pageable);
}
