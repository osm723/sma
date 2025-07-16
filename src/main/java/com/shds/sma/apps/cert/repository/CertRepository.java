package com.shds.sma.apps.cert.repository;

import com.shds.sma.apps.cert.entity.Cert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface CertRepository extends JpaRepository<Cert, Long>, CertQueryRepository {

    Page<Cert> findByEndDateBefore(LocalDate currentDate, Pageable pageable);
}
