package com.shds.sma.external.api.dto.cert;

import com.shds.sma.external.api.dto.common.ApiApproval;
import com.shds.sma.external.api.dto.common.ApiMember;
import com.shds.sma.external.api.dto.common.ApiSystem;
import com.shds.sma.apps.cert.entity.Cert;
import com.shds.sma.apps.cert.types.CertType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ApiCertResponseDto {

    private Long certId;

    private CertType certType;

    private String certName;

    private ApiSystem applySystem;

    private String content;

    private String siteLink;

    private LocalDate startDate;

    private LocalDate endDate;

    private ApiMember member;

    private ApiApproval approval;

    private String validity;

    public ApiCertResponseDto(Cert cert) {
        this.certId = cert.getId();
        this.certType = cert.getCertType();
        this.certName = cert.getCertName();
        this.applySystem = new ApiSystem(cert.getApplySystem());
        this.content = cert.getContent();
        this.siteLink = cert.getSiteLink();
        this.startDate = cert.getStartDate();
        this.endDate = cert.getEndDate();
        this.member = new ApiMember(cert.getMember());
        this.approval = new ApiApproval(cert.getApproval());
        this.validity = cert.getValidity().name();
    }
}
