package com.shds.sma.api.dto.cert;

import com.shds.sma.api.dto.common.ApiApproval;
import com.shds.sma.cert.types.CertType;
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
