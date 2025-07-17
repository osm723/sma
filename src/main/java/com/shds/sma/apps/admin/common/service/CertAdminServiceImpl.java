package com.shds.sma.apps.admin.common.service;

import com.shds.sma.apps.admin.common.entity.Approval;
import com.shds.sma.apps.admin.member.entity.Member;
import com.shds.sma.apps.admin.common.repository.ApprovalRepository;
import com.shds.sma.apps.admin.member.repository.MemberRepository;
import com.shds.sma.apps.cert.dto.CertModRequestDto;
import com.shds.sma.apps.cert.dto.CertRequestDto;
import com.shds.sma.apps.cert.dto.CertResponseDto;
import com.shds.sma.apps.cert.dto.CertSaveRequestDto;
import com.shds.sma.apps.cert.entity.Cert;
import com.shds.sma.apps.cert.repository.CertRepository;
import com.shds.sma.apps.system.entity.System;
import com.shds.sma.apps.system.repository.SystemRepository;
import com.shds.sma.common.exception.BizException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.shds.sma.common.exception.ExceptionMessageConst.*;
import static com.shds.sma.common.exception.ExceptionMessageConst.NOT_FOUND_APPROVAL;
import static com.shds.sma.common.exception.ExceptionMessageConst.NOT_FOUND_CERT;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CertAdminServiceImpl implements CertAdminService {

    private final CertRepository certRepository;

    private final MemberRepository memberRepository;

    private final ApprovalRepository approvalRepository;

    private final SystemRepository systemRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<CertResponseDto> findCertByCond(CertRequestDto certRequestDto, Pageable pageable) {
        Page<Cert> findCert = certRepository.findCertByCond(certRequestDto, pageable);
        return findCert.map(CertResponseDto::new);
    }

    @Override
    @Transactional(readOnly = true)
    public CertResponseDto findCertById(Long certId) {
        Cert findCert = getCert(certId);
        return modelMapper.map(findCert, CertResponseDto.class);
    }

    @Override
    public void saveCert(CertSaveRequestDto certSaveRequestDto) {
        Approval savedApproval = new Approval();
        if (certSaveRequestDto.useApproval()) {
            savedApproval = saveCertApproval(certSaveRequestDto);
        }

        com.shds.sma.apps.system.entity.System findSystem = getSystem(certSaveRequestDto.getApplySystemId());
        Member findMember = getMember(certSaveRequestDto.getMemberId());

        certRepository.save(buildCert(certSaveRequestDto, findSystem, findMember, savedApproval));
    }

    @Override
    public void modifiedCert(CertModRequestDto certModRequestDto) {
        if (certModRequestDto.getApprovalId() != null) {
            Approval findApproval = getApproval(certModRequestDto.getApprovalId());
            findApproval.approvalCertModified(certModRequestDto);
            certModRequestDto.setApproval(findApproval);
        }

        Cert findCert = getCert(certModRequestDto.getCertId());

        System findSystem = getSystem(certModRequestDto.getApplySystemId());
        certModRequestDto.setApplySystem(findSystem);

        Member findMember = getMember(certModRequestDto.getMemberId());
        certModRequestDto.setMember(findMember);

        findCert.certModified(certModRequestDto);
    }

    @Override
    public void removeCert(Long certId) {
        Cert findCert = getCert(certId);
        findCert.setValidityN();
    }

    @Override
    public void useCert(Long certId) {
        Cert findCert = getCert(certId);
        findCert.setValidityY();
    }

    private System getSystem(Long systemId) {
        return systemRepository.findById(systemId).orElseThrow(() -> new BizException(NOT_FOUND_SYSTEM));
    }

    private Member getMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new BizException(NOT_FOUND_MEMBER));
    }

    private Approval getApproval(Long approvalId) {
        return approvalRepository.findById(approvalId).orElseThrow(() -> new BizException(NOT_FOUND_APPROVAL));
    }

    private Cert getCert(Long certId) {
        return certRepository.findById(certId).orElseThrow(() -> new BizException(NOT_FOUND_CERT));
    }

    private Approval saveCertApproval(CertSaveRequestDto certSaveRequestDto) {
        Approval saveApproval = Approval.builder()
                .approvalNo(certSaveRequestDto.getApprovalNo())
                .drafterId(certSaveRequestDto.getDrafterId())
                .degree(certSaveRequestDto.getDegree())
                .approverId(certSaveRequestDto.getApproverId())
                .approvalStatus(certSaveRequestDto.getApprovalStatus())
                .approveDate(certSaveRequestDto.getApproveDate())
                .cancelDate(certSaveRequestDto.getCancelDate()).build();

        return approvalRepository.save(saveApproval);
    }

    private static Cert buildCert(CertSaveRequestDto certSaveRequestDto, System findSystem, Member findMember, Approval savedApproval) {
        return Cert.builder()
                .certType(certSaveRequestDto.getCertType())
                .certName(certSaveRequestDto.getCertName())
                .applySystem(findSystem)
                .content(certSaveRequestDto.getContent())
                .siteLink(certSaveRequestDto.getSiteLink())
                .startDate(certSaveRequestDto.getStartDate())
                .endDate(certSaveRequestDto.getEndDate())
                .member(findMember)
                .approval(savedApproval).build();
    }
}
