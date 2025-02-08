package com.shds.sma.cert.repository;

import com.shds.sma.cert.entity.Cert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertRepository extends JpaRepository<Cert, Long>, CertQueryRepository {
}
