package com.shds.sma.api.service;

import com.shds.sma.admin.entity.Member;
import com.shds.sma.common.exception.ExceptionMessageConst;
import com.shds.sma.system.entity.System;
import com.shds.sma.admin.repository.approval.ApprovalRepository;
import com.shds.sma.admin.repository.member.MemberRepository;
import com.shds.sma.system.repository.SystemRepository;
import com.shds.sma.api.dto.common.ApiApproval;
import com.shds.sma.api.dto.ip.ApiIpModRequestDto;
import com.shds.sma.api.dto.ip.ApiIpResponseDto;
import com.shds.sma.api.dto.ip.ApiIpSaveRequestDto;
import com.shds.sma.admin.entity.Approval;
import com.shds.sma.common.exception.BizException;
import com.shds.sma.ip.entity.Ip;
import com.shds.sma.ip.repository.IpRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.shds.sma.common.exception.ExceptionMessageConst.*;

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
        Ip ip = ipRepository.findById(ipId).orElseThrow(() -> new BizException(NOT_FOUND_IP));
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
        Member saveMember = memberRepository.findById(memberId).orElseThrow(() -> new BizException(NOT_FOUND_MEMBER));

        Ip saveIp = Ip.builder()
                .ipType(apiIpSaveRequestDto.getIpType())
                .startIpAddr(apiIpSaveRequestDto.getStartIpAddr())
                .endIpAddr(apiIpSaveRequestDto.getEndIpAddr())
                .port(apiIpSaveRequestDto.getPort())
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
            updateApproval = approvalRepository.findById(apiIpModRequestDto.getApproval().getApprovalId()).orElseThrow(() -> new BizException(NOT_FOUND_APPROVAL));
            updateApproval.approvalApiIpModified(apiIpModRequestDto.getApproval());
            apiIpModRequestDto.setApproval(new ApiApproval(updateApproval));
        }

        String systemName = apiIpModRequestDto.getSystemName();
        System updateSystem = systemRepository.findBySystemName(systemName);
        apiIpModRequestDto.setSystem(updateSystem);

        Long memberId = apiIpModRequestDto.getMemberId();
        Member updateMember = memberRepository.findById(memberId).orElseThrow(() -> new BizException(NOT_FOUND_MEMBER));
        apiIpModRequestDto.setMember(updateMember);

        Ip updatedIp = ipRepository.findById(apiIpModRequestDto.getIpId()).orElseThrow(() -> new BizException(NOT_FOUND_IP));
        updatedIp.apiIpModified(apiIpModRequestDto);

        return modelMapper.map(updatedIp, ApiIpResponseDto.class);
    }

    @Override
    public void deleteIp(Long ipId) {
        Ip deletedIp = ipRepository.findById(ipId).orElseThrow(() -> new BizException(NOT_FOUND_IP));
        deletedIp.setValidityN();
    }
}
