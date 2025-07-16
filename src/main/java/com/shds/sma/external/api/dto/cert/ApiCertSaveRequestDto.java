package com.shds.sma.external.api.dto.cert;

import com.shds.sma.external.api.dto.common.ApiApproval;
import com.shds.sma.apps.cert.types.CertType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ApiCertSaveRequestDto {

    private CertType certType;

    private String certName;

    private String systemName;

    private String content;

    private String siteLink;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long memberId;

    private ApiApproval approval;

}
