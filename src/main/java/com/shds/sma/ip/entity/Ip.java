package com.shds.sma.ip.entity;

import com.shds.sma.api.dto.ip.ApiIpModRequestDto;
import com.shds.sma.ip.dto.IpModRequestDto;
import com.shds.sma.common.entity.BaseEntity;
import com.shds.sma.admin.entity.System;
import com.shds.sma.common.entity.Approval;
import com.shds.sma.ip.types.IpType;
import com.shds.sma.admin.entity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "SMA_IP")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ip extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "IP_ID")
    private Long id;

    @Column(length = 20, columnDefinition = "VARCHAR(20) COMMENT 'IP 타입'", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private IpType ipType;

    @Column(length = 200, columnDefinition = "VARCHAR(200) COMMENT '출발지 IP 주소'", nullable = false)
    @Size(max = 200)
    @NotBlank
    private String startIpAddr;

    @Column(length = 200, columnDefinition = "VARCHAR(200) COMMENT '도착지 IP 주소'", nullable = false)
    @Size(max = 200)
    @NotBlank
    private String endIpAddr;

    @ManyToOne
    @JoinColumn(name = "SYSTEM_ID")
    private System applySystem;

    @Column(length = 400, columnDefinition = "VARCHAR(400) COMMENT '내용'")
    @Size(max = 400)
    private String content;

    @Column(length = 400, columnDefinition = "VARCHAR(400) COMMENT '사이트주소'")
    @Size(max = 400)
    private String siteLink;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate startDate;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "APPROVAL_ID")
    private Approval approval;

    @Builder
    public Ip(IpType ipType, String startIpAddr, String endIpAddr, System applySystem, String content, String siteLink, LocalDate startDate, LocalDate endDate, Member member, Approval approval) {
        this.ipType = ipType;
        this.startIpAddr = startIpAddr;
        this.endIpAddr = endIpAddr;
        this.applySystem = applySystem;
        this.content = content;
        this.siteLink = siteLink;
        this.startDate = startDate;
        this.endDate = endDate;
        this.member = member;
        this.approval = approval;
    }

    public void apiIpModified(ApiIpModRequestDto apiIpModRequestDto) {
        this.ipType = apiIpModRequestDto.getIpType();
        this.startIpAddr = apiIpModRequestDto.getStartIpAddr();
        this.endIpAddr = apiIpModRequestDto.getEndIpAddr();
        this.applySystem = apiIpModRequestDto.getSystem();
        this.content = apiIpModRequestDto.getContent();
        this.siteLink = apiIpModRequestDto.getSiteLink();
        this.startDate = apiIpModRequestDto.getStartDate();
        this.endDate = apiIpModRequestDto.getEndDate();
        this.member = apiIpModRequestDto.getMember();
        this.approval = new Approval(apiIpModRequestDto.getApproval());
    }

    public void ipModified(IpModRequestDto ipModRequestDto) {
        this.ipType = ipModRequestDto.getIpType();
        this.startIpAddr = ipModRequestDto.getStartIpAddr();
        this.endIpAddr = ipModRequestDto.getEndIpAddr();
        this.applySystem = ipModRequestDto.getApplySystem();
        this.content = ipModRequestDto.getContent();
        this.siteLink = ipModRequestDto.getSiteLink();
        this.startDate = ipModRequestDto.getStartDate();
        this.endDate = ipModRequestDto.getEndDate();
        this.member = ipModRequestDto.getMember();
        this.approval = ipModRequestDto.getApproval();
    }
}
