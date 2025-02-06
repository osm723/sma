package com.shds.sma.manage.entity;

import com.shds.sma.admin.dto.ip.IpModRequestDto;
import com.shds.sma.common.entity.BaseEntity;
import com.shds.sma.admin.entity.System;
import com.shds.sma.manage.types.IpType;
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
    private IpType ipType;

    @Column(length = 200, columnDefinition = "VARCHAR(200) COMMENT '출발지 IP 주소'", nullable = false)
    @Size(max = 200)
    @NotBlank
    private String startIpAddr;

    @Column(length = 200, columnDefinition = "VARCHAR(200) COMMENT '도착지 IP 주소'", nullable = false)
    @Size(max = 200)
    @NotBlank
    private String endIpAddr;

    @OneToOne
    @JoinColumn(name = "SYSTEM_ID")
    private System system;

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

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne
    @JoinColumn(name = "APPROVAL_ID")
    private Approval approval;

    @Builder
    public Ip(IpType ipType, String startIpAddr, String endIpAddr, System system, String content, String siteLink, LocalDate startDate, LocalDate endDate, Member member, Approval approval) {
        this.ipType = ipType;
        this.startIpAddr = startIpAddr;
        this.endIpAddr = endIpAddr;
        this.system = system;
        this.content = content;
        this.siteLink = siteLink;
        this.startDate = startDate;
        this.endDate = endDate;
        this.member = member;
        this.approval = approval;
    }

    public void ipModified(IpModRequestDto ipModRequestDto) {
        this.ipType = ipModRequestDto.getIpType();
        this.startIpAddr = ipModRequestDto.getStartIpAddr();
        this.endIpAddr = ipModRequestDto.getEndIpAddr();
        this.system = ipModRequestDto.getSystem();
        this.content = ipModRequestDto.getContent();
        this.siteLink = ipModRequestDto.getSiteLink();
        this.startDate = ipModRequestDto.getStartDate();
        this.endDate = ipModRequestDto.getEndDate();
        this.member = ipModRequestDto.getMember();
        this.approval = ipModRequestDto.getApproval();
    }
}
