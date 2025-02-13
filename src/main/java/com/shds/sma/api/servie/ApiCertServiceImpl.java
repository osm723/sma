package com.shds.sma.api.servie;

import com.shds.sma.admin.entity.Member;
import com.shds.sma.admin.entity.System;
import com.shds.sma.admin.repositroy.approval.ApprovalRepository;
import com.shds.sma.admin.repositroy.member.MemberRepository;
import com.shds.sma.admin.repositroy.system.SystemRepository;
import com.shds.sma.api.dto.cert.ApiCertModRequestDto;
import com.shds.sma.api.dto.cert.ApiCertResponseDto;
import com.shds.sma.api.dto.cert.ApiCertSaveRequestDto;
import com.shds.sma.api.dto.common.ApiApproval;
import com.shds.sma.cert.dto.CertModRequestDto;
import com.shds.sma.cert.entity.Cert;
import com.shds.sma.cert.repository.CertRepository;
import com.shds.sma.common.entity.Approval;
import com.shds.sma.common.exception.BizException;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ApiCertServiceImpl implements ApiCertService {

    private final CertRepository certRepository;

    private final SystemRepository systemRepository;

    private final MemberRepository memberRepository;

    private final ApprovalRepository approvalRepository;

    private final EntityManager em;

    private final ModelMapper modelMapper;

    @Override
    public Page<ApiCertResponseDto> getAllCerts(Pageable pageable) {
        Page<Cert> certs = certRepository.findAll(pageable);
        return certs.map(ApiCertResponseDto::new);
    }

    @Override
    public ApiCertResponseDto getCert(Long certId) {
        Cert cert = certRepository.findById(certId).orElseThrow(() -> new BizException("존재하지 않는 인증서 입니다."));
        return modelMapper.map(cert, ApiCertResponseDto.class);
    }

    @Override
    public ApiCertResponseDto createCert(ApiCertSaveRequestDto apiCertSaveRequestDto) {
        Approval saveApproval = new Approval();
        if (apiCertSaveRequestDto.getApproval() != null) {
            saveApproval = Approval.builder()
                    .approvalNo(apiCertSaveRequestDto.getApproval().getApprovalNo())
                    .drafterId(apiCertSaveRequestDto.getApproval().getDrafterId())
                    .degree(apiCertSaveRequestDto.getApproval().getDegree())
                    .approverId(apiCertSaveRequestDto.getApproval().getApproverId())
                    .approvalStatus(apiCertSaveRequestDto.getApproval().getApprovalStatus())
                    .approveDate(apiCertSaveRequestDto.getApproval().getApproveDate())
                    .cancelDate(apiCertSaveRequestDto.getApproval().getCancelDate()).build();

            approvalRepository.save(saveApproval);
        }

        String systemName = apiCertSaveRequestDto.getSystemName();
        System saveSystem = systemRepository.findBySystemName(systemName);

        Long memberId = apiCertSaveRequestDto.getMemberId();
        Member saveMember = memberRepository.findById(memberId).orElseThrow(() -> new BizException("존재하지 않는 직원 입니다."));

        Cert savrCert = Cert.builder()
                .certType(apiCertSaveRequestDto.getCertType())
                .certName(apiCertSaveRequestDto.getCertName())
                .applySystem(saveSystem)
                .content(apiCertSaveRequestDto.getContent())
                .siteLink(apiCertSaveRequestDto.getSiteLink())
                .startDate(apiCertSaveRequestDto.getStartDate())
                .endDate(apiCertSaveRequestDto.getEndDate())
                .member(saveMember)
                .approval(saveApproval).build();

        Cert createdCert = certRepository.save(savrCert);
        return modelMapper.map(createdCert, ApiCertResponseDto.class);
    }
    @Override
    public ApiCertResponseDto updateCert(ApiCertModRequestDto apiCertModRequestDto) {
        Approval updateApproval = new Approval();
        if (apiCertModRequestDto.getApproval() != null) {
            updateApproval = approvalRepository.findById(apiCertModRequestDto.getApproval().getApprovalId()).orElseThrow(() -> new BizException("존재하지 않는 결재 입니다."));
            updateApproval.approvalApiCertModified(apiCertModRequestDto.getApproval());
            apiCertModRequestDto.setApproval(new ApiApproval(updateApproval));
        }

        String systemName = apiCertModRequestDto.getSystemName();
        System updateSystem = systemRepository.findBySystemName(systemName);
        apiCertModRequestDto.setSystem(updateSystem);

        Long memberId = apiCertModRequestDto.getMemberId();
        Member updateMember = memberRepository.findById(memberId).orElseThrow(() -> new BizException("존재하지 않는 직원 입니다."));
        apiCertModRequestDto.setMember(updateMember);

        Cert updatedCert = certRepository.findById(apiCertModRequestDto.getCertId()).orElseThrow(() -> new BizException("존재하지 않는 인증서 입니다."));
        updatedCert.apiCertModified(apiCertModRequestDto);
        return modelMapper.map(updatedCert, ApiCertResponseDto.class);
    }
    @Override
    public void deleteCert(Long certId) {
        Cert deletedCert = certRepository.findById(certId).orElseThrow(() -> new BizException("존재하지 않는 인증서 입니다."));
        deletedCert.setValidityN();
    }
}
