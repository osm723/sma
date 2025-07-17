package com.shds.sma.external.api.service;

import com.shds.sma.apps.admin.entity.Member;
import com.shds.sma.apps.system.entity.System;
import com.shds.sma.apps.admin.repository.approval.ApprovalRepository;
import com.shds.sma.apps.admin.repository.member.MemberRepository;
import com.shds.sma.apps.system.repository.SystemRepository;
import com.shds.sma.external.api.dto.common.ApiApproval;
import com.shds.sma.external.api.dto.ip.ApiIpModRequestDto;
import com.shds.sma.external.api.dto.ip.ApiIpResponseDto;
import com.shds.sma.external.api.dto.ip.ApiIpSaveRequestDto;
import com.shds.sma.apps.admin.entity.Approval;
import com.shds.sma.common.exception.BizException;
import com.shds.sma.apps.ip.entity.Ip;
import com.shds.sma.apps.ip.repository.IpRepository;
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
        Ip ip = getIpById(ipId);
        return modelMapper.map(ip, ApiIpResponseDto.class);
    }

    @Override
    public ApiIpResponseDto createIp(ApiIpSaveRequestDto apiIpSaveRequestDto) {
        Approval saveApproval = new Approval();
        if (apiIpSaveRequestDto.getApproval() != null) {
            approvalRepository.save(buildApproval(apiIpSaveRequestDto));
        }

        System saveSystem = systemRepository.findBySystemName(apiIpSaveRequestDto.getSystemName());
        Member saveMember = memberRepository.findById(apiIpSaveRequestDto.getMemberId()).orElseThrow(() -> new BizException(NOT_FOUND_MEMBER));

        Ip saveIp = buildIp(apiIpSaveRequestDto, saveSystem, saveMember, saveApproval);
        Ip createdIp = ipRepository.save(saveIp);

        return modelMapper.map(createdIp, ApiIpResponseDto.class);
    }

    @Override
    public ApiIpResponseDto updateIp(ApiIpModRequestDto apiIpModRequestDto) {
        Approval updateApproval;
        if (apiIpModRequestDto.getApproval() != null) {
            updateApproval = approvalRepository.findById(apiIpModRequestDto.getApproval().getApprovalId()).orElseThrow(() -> new BizException(NOT_FOUND_APPROVAL));
            updateApproval.approvalApiIpModified(apiIpModRequestDto.getApproval());
            apiIpModRequestDto.setApproval(new ApiApproval(updateApproval));
        }

        System updateSystem = systemRepository.findBySystemName(apiIpModRequestDto.getSystemName());
        apiIpModRequestDto.setSystem(updateSystem);

        Member updateMember = memberRepository.findById(getALong(apiIpModRequestDto)).orElseThrow(() -> new BizException(NOT_FOUND_MEMBER));
        apiIpModRequestDto.setMember(updateMember);

        Ip updatedIp = getIpById(apiIpModRequestDto.getIpId());
        updatedIp.apiIpModified(apiIpModRequestDto);

        return modelMapper.map(updatedIp, ApiIpResponseDto.class);
    }

    @Override
    public void deleteIp(Long ipId) {
        Ip deletedIp = getIpById(ipId);
        deletedIp.setValidityN();
    }

    private Ip getIpById(Long ipId) {
        return ipRepository.findById(ipId).orElseThrow(() -> new BizException(NOT_FOUND_IP));
    }

    private static Long getALong(ApiIpModRequestDto apiIpModRequestDto) {
        return apiIpModRequestDto.getMemberId();
    }

    private static Ip buildIp(ApiIpSaveRequestDto apiIpSaveRequestDto, System saveSystem, Member saveMember, Approval saveApproval) {
        return Ip.builder()
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
    }

    private static Approval buildApproval(ApiIpSaveRequestDto apiIpSaveRequestDto) {
        return Approval.builder()
                .approvalNo(apiIpSaveRequestDto.getApproval().getApprovalNo())
                .drafterId(apiIpSaveRequestDto.getApproval().getDrafterId())
                .degree(apiIpSaveRequestDto.getApproval().getDegree())
                .approverId(apiIpSaveRequestDto.getApproval().getApproverId())
                .approvalStatus(apiIpSaveRequestDto.getApproval().getApprovalStatus())
                .approveDate(apiIpSaveRequestDto.getApproval().getApproveDate())
                .cancelDate(apiIpSaveRequestDto.getApproval().getCancelDate()).build();
    }
}
