package com.shds.sma.apps.admin.common.service;

import com.shds.sma.apps.admin.common.entity.Approval;
import com.shds.sma.apps.admin.member.entity.Member;
import com.shds.sma.apps.admin.common.repository.ApprovalRepository;
import com.shds.sma.apps.admin.member.repository.MemberRepository;
import com.shds.sma.apps.ip.dto.IpModRequestDto;
import com.shds.sma.apps.ip.dto.IpRequestDto;
import com.shds.sma.apps.ip.dto.IpResponseDto;
import com.shds.sma.apps.ip.dto.IpSaveRequestDto;
import com.shds.sma.apps.ip.entity.Ip;
import com.shds.sma.apps.ip.repository.IpRepository;
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
import static com.shds.sma.common.exception.ExceptionMessageConst.NOT_FOUND_MEMBER;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class IpAdminServiceImpl implements IpAdminService {

    private final IpRepository ipRepository;

    private final MemberRepository memberRepository;

    private final ApprovalRepository approvalRepository;

    private final SystemRepository systemRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<IpResponseDto> findIpByCond(IpRequestDto ipRequestDto, Pageable pageable) {
        Page<Ip> findIp = ipRepository.findIpByCond(ipRequestDto, pageable);
        return findIp.map(IpResponseDto::new);
    }

    @Override
    @Transactional(readOnly = true)
    public IpResponseDto findIpById(Long ipId) {
        Ip findIp = getIp(ipId);
        return modelMapper.map(findIp, IpResponseDto.class);
    }

    @Override
    public void saveIp(IpSaveRequestDto ipSaveRequestDto) {
        Approval savedApproval = new Approval();
        if (ipSaveRequestDto.useApproval()) {
            savedApproval = saveIpApproval(ipSaveRequestDto);
        }

        System findSystem = getSystem(ipSaveRequestDto.getApplySystemId());

        Member findMember = getMember(ipSaveRequestDto.getMemberId());

        ipRepository.save(buildIp(ipSaveRequestDto, findSystem, findMember, savedApproval));
    }

    @Override
    public void modifiedIp(IpModRequestDto ipModRequestDto) {
        if (ipModRequestDto.getApprovalId() != null) {
            Approval findApproval = getApproval(ipModRequestDto.getApprovalId());
            findApproval.approvalIpModified(ipModRequestDto);
            ipModRequestDto.setApproval(findApproval);
        }

        Ip findIp = getIp(ipModRequestDto.getIpId());

        System findSystem = getSystem(ipModRequestDto.getApplySystemId());
        ipModRequestDto.setApplySystem(findSystem);

        Member findMember = getMember(ipModRequestDto.getMemberId());
        ipModRequestDto.setMember(findMember);

        findIp.ipModified(ipModRequestDto);
    }

    @Override
    public void removeIp(Long ipId) {
        Ip findIp = getIp(ipId);
        findIp.setValidityN();
    }

    @Override
    public void useIp(Long ipId) {
        Ip findIp = getIp(ipId);
        findIp.setValidityY();
    }

    private Ip getIp(Long ipId) {
        return ipRepository.findById(ipId).orElseThrow(() -> new BizException(NOT_FOUND_IP));
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

    private Approval saveIpApproval(IpSaveRequestDto ipSaveRequestDto) {
        Approval saveApproval = Approval.builder()
                .approvalNo(ipSaveRequestDto.getApprovalNo())
                .drafterId(ipSaveRequestDto.getDrafterId())
                .degree(ipSaveRequestDto.getDegree())
                .approverId(ipSaveRequestDto.getApproverId())
                .approvalStatus(ipSaveRequestDto.getApprovalStatus())
                .approveDate(ipSaveRequestDto.getApproveDate())
                .cancelDate(ipSaveRequestDto.getCancelDate()).build();

        return approvalRepository.save(saveApproval);
    }

    private static Ip buildIp(IpSaveRequestDto ipSaveRequestDto, System findSystem, Member findMember, Approval savedApproval) {
        return Ip.builder()
                .ipType(ipSaveRequestDto.getIpType())
                .startIpAddr(ipSaveRequestDto.getStartIpAddr())
                .endIpAddr(ipSaveRequestDto.getEndIpAddr())
                .port(ipSaveRequestDto.getPort())
                .applySystem(findSystem)
                .content(ipSaveRequestDto.getContent())
                .siteLink(ipSaveRequestDto.getSiteLink())
                .startDate(ipSaveRequestDto.getStartDate())
                .endDate(ipSaveRequestDto.getEndDate())
                .member(findMember)
                .approval(savedApproval).build();
    }
}
