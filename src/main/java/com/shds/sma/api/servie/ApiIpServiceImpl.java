package com.shds.sma.api.servie;

import com.shds.sma.admin.entity.Member;
import com.shds.sma.admin.entity.System;
import com.shds.sma.admin.repositroy.approval.ApprovalRepository;
import com.shds.sma.admin.repositroy.member.MemberRepository;
import com.shds.sma.admin.repositroy.system.SystemRepository;
import com.shds.sma.api.dto.cert.ApiCertResponseDto;
import com.shds.sma.api.dto.common.ApiApproval;
import com.shds.sma.api.dto.ip.ApiIpModRequestDto;
import com.shds.sma.api.dto.ip.ApiIpResponseDto;
import com.shds.sma.api.dto.ip.ApiIpSaveRequestDto;
import com.shds.sma.cert.entity.Cert;
import com.shds.sma.common.entity.Approval;
import com.shds.sma.common.exception.BizException;
import com.shds.sma.ip.entity.Ip;
import com.shds.sma.ip.repository.IpRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ApiIpServiceImpl implements ApiIpService {

    private final IpRepository ipRepository;

    private final ApprovalRepository approvalRepository;

    private final SystemRepository systemRepository;

    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;

    @Override
    public Page<ApiIpResponseDto> getAllIps(Pageable pageable) {
        Page<Ip> ips = ipRepository.findAll(pageable);
        return ips.map(ApiIpResponseDto::new);
    }

    @Override
    public ApiIpResponseDto getIp(Long ipId) {
        Ip ip = ipRepository.findById(ipId).orElseThrow(() -> new BizException("존재하지 않는 IP 입니다."));
        return modelMapper.map(ip, ApiIpResponseDto.class);
    }

    @Override
    public ApiIpResponseDto createIp(ApiIpSaveRequestDto apiIpSaveRequestDto) {
        Approval saveApproval = new Approval();
        if (apiIpSaveRequestDto.getApproval() != null) {
            saveApproval = Approval.builder()
                    .approvalNo(apiIpSaveRequestDto.getApproval().getApprovalNo())
                    .drafterId(apiIpSaveRequestDto.getApproval().getDrafterId())
                    .degree(apiIpSaveRequestDto.getApproval().getDegree())
                    .approverId(apiIpSaveRequestDto.getApproval().getApproverId())
                    .approvalStatus(apiIpSaveRequestDto.getApproval().getApprovalStatus())
                    .approveDate(apiIpSaveRequestDto.getApproval().getApproveDate())
                    .cancelDate(apiIpSaveRequestDto.getApproval().getCancelDate()).build();

            approvalRepository.save(saveApproval);
        }

        String systemName = apiIpSaveRequestDto.getSystemName();
        System saveSystem = systemRepository.findBySystemName(systemName);

        Long memberId = apiIpSaveRequestDto.getMemberId();
        Member saveMember = memberRepository.findById(memberId).orElseThrow(() -> new BizException("존재하지 않는 직원 입니다."));

        Ip saveIp = Ip.builder()
                .ipType(apiIpSaveRequestDto.getIpType())
                .startIpAddr(apiIpSaveRequestDto.getStartIpAddr())
                .endIpAddr(apiIpSaveRequestDto.getEndIpAddr())
                .applySystem(saveSystem)
                .content(apiIpSaveRequestDto.getContent())
                .siteLink(apiIpSaveRequestDto.getSiteLink())
                .startDate(apiIpSaveRequestDto.getStartDate())
                .endDate(apiIpSaveRequestDto.getEndDate())
                .member(saveMember)
                .approval(saveApproval).build();

        Ip createdIp = ipRepository.save(saveIp);
        return modelMapper.map(createdIp, ApiIpResponseDto.class);
    }

    @Override
    public ApiIpResponseDto updateIp(ApiIpModRequestDto apiIpModRequestDto) {
        Approval updateApproval = new Approval();
        if (apiIpModRequestDto.getApproval() != null) {
            //updateApproval = approvalRepository.findById(apiIpModRequestDto.getApproval().getId()).orElseThrow(() -> new BizException("존재하지 않는 결재 입니다."));
            //updateApproval.approvalIpModified(apiIpModRequestDto);
            apiIpModRequestDto.setApproval(new ApiApproval(updateApproval));
        }

        String systemName = apiIpModRequestDto.getSystemName();
        System updateSystem = systemRepository.findBySystemName(systemName);
        apiIpModRequestDto.setSystem(updateSystem);

        Long memberId = apiIpModRequestDto.getMemberId();
        Member updateMember = memberRepository.findById(memberId).orElseThrow(() -> new BizException("존재하지 않는 직원 입니다."));
        apiIpModRequestDto.setMember(updateMember);

        Ip updatedIp = ipRepository.findById(apiIpModRequestDto.getIpId()).orElseThrow(() -> new BizException("존재하지 않는 IP 입니다."));
        //updatedIp.ipModified(apiIpModRequestDto);
        return modelMapper.map(updatedIp, ApiIpResponseDto.class);
    }

    @Override
    public void deleteIp(Long ipId) {
        Ip deletedIp = ipRepository.findById(ipId).orElseThrow(() -> new BizException("존재하지 않는 IP 입니다."));
        deletedIp.setValidityN();
    }
}
