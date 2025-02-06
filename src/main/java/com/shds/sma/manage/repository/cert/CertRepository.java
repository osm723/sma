package com.shds.sma.manage.repository.cert;

import com.shds.sma.manage.entity.Cert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertRepository extends JpaRepository<Cert, Long>, CertQueryRepository {
}
