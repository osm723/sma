package com.shds.sma.external.api.service;

import com.shds.sma.apps.admin.entity.Member;
import com.shds.sma.apps.system.entity.System;
import com.shds.sma.apps.admin.repository.approval.ApprovalRepository;
import com.shds.sma.apps.admin.repository.member.MemberRepository;
import com.shds.sma.apps.system.repository.SystemRepository;
import com.shds.sma.external.api.dto.cert.ApiCertModRequestDto;
import com.shds.sma.external.api.dto.cert.ApiCertResponseDto;
import com.shds.sma.external.api.dto.cert.ApiCertSaveRequestDto;
import com.shds.sma.external.api.dto.common.ApiApproval;
import com.shds.sma.apps.cert.entity.Cert;
import com.shds.sma.apps.cert.repository.CertRepository;
import com.shds.sma.apps.admin.entity.Approval;
import com.shds.sma.common.exception.BizException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.shds.sma.common.exception.ExceptionMessageConst.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ApiCertServiceImpl implements ApiCertService {

    private final CertRepository certRepository;

    private final SystemRepository systemRepository;

    private final MemberRepository memberRepository;

    private final ApprovalRepository approvalRepository;

    private final ModelMapper modelMapper;

    @Override
    public Page<ApiCertResponseDto> getAllCerts(Pageable pageable) {
        Page<Cert> certs = certRepository.findAll(pageable);
        return certs.map(ApiCertResponseDto::new);
    }

    @Override
    public ApiCertResponseDto getCert(Long certId) {
        Cert cert = getCertById(certId);
        return modelMapper.map(cert, ApiCertResponseDto.class);
    }

    @Override
    public ApiCertResponseDto createCert(ApiCertSaveRequestDto apiCertSaveRequestDto) {
        Approval saveApproval = null;
        if (apiCertSaveRequestDto.getApproval() != null) {
            approvalRepository.save(buildApproval(apiCertSaveRequestDto));
        }

        System saveSystem = systemRepository.findBySystemName(apiCertSaveRequestDto.getSystemName());
        Member saveMember = memberRepository.findById(apiCertSaveRequestDto.getMemberId()).orElseThrow(() -> new BizException(NOT_FOUND_MEMBER));

        Cert saveCert = buildCert(apiCertSaveRequestDto, saveSystem, saveMember, saveApproval);
        Cert createdCert = certRepository.save(saveCert);

        return modelMapper.map(createdCert, ApiCertResponseDto.class);
    }

    @Override
    public ApiCertResponseDto updateCert(ApiCertModRequestDto apiCertModRequestDto) {
        Approval updateApproval;
        if (apiCertModRequestDto.getApproval() != null) {
            updateApproval = approvalRepository.findById(apiCertModRequestDto.getApproval().getApprovalId()).orElseThrow(() -> new BizException(NOT_FOUND_APPROVAL));
            updateApproval.approvalApiCertModified(apiCertModRequestDto.getApproval());
            apiCertModRequestDto.setApproval(new ApiApproval(updateApproval));
        }

        System updateSystem = systemRepository.findBySystemName(apiCertModRequestDto.getSystemName());
        apiCertModRequestDto.setSystem(updateSystem);

        Member updateMember = memberRepository.findById(apiCertModRequestDto.getMemberId()).orElseThrow(() -> new BizException(NOT_FOUND_MEMBER));
        apiCertModRequestDto.setMember(updateMember);

        Cert updatedCert = getCertById(apiCertModRequestDto.getCertId());
        updatedCert.apiCertModified(apiCertModRequestDto);

        return modelMapper.map(updatedCert, ApiCertResponseDto.class);
    }
    @Override
    public void deleteCert(Long certId) {
        Cert deletedCert = getCertById(certId);
        deletedCert.setValidityN();
    }

    private Cert getCertById(Long certId) {
        return certRepository.findById(certId).orElseThrow(() -> new BizException(NOT_FOUND_CERT));
    }

    private static Approval buildApproval(ApiCertSaveRequestDto apiCertSaveRequestDto) {
        Approval saveApproval;
        saveApproval = Approval.builder()
                .approvalNo(apiCertSaveRequestDto.getApproval().getApprovalNo())
                .drafterId(apiCertSaveRequestDto.getApproval().getDrafterId())
                .degree(apiCertSaveRequestDto.getApproval().getDegree())
                .approverId(apiCertSaveRequestDto.getApproval().getApproverId())
                .approvalStatus(apiCertSaveRequestDto.getApproval().getApprovalStatus())
                .approveDate(apiCertSaveRequestDto.getApproval().getApproveDate())
                .cancelDate(apiCertSaveRequestDto.getApproval().getCancelDate()).build();
        return saveApproval;
    }

    private static Cert buildCert(ApiCertSaveRequestDto apiCertSaveRequestDto, System saveSystem, Member saveMember, Approval saveApproval) {
        return Cert.builder()
                .certType(apiCertSaveRequestDto.getCertType())
                .certName(apiCertSaveRequestDto.getCertName())
                .applySystem(saveSystem)
                .content(apiCertSaveRequestDto.getContent())
                .siteLink(apiCertSaveRequestDto.getSiteLink())
                .startDate(apiCertSaveRequestDto.getStartDate())
                .endDate(apiCertSaveRequestDto.getEndDate())
                .member(saveMember)
                .approval(saveApproval).build();
    }
}
