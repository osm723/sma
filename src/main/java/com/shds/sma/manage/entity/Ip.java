package com.shds.sma.manage.entity;

import com.shds.sma.common.entity.BaseEntity;
import com.shds.sma.admin.entity.System;
import com.shds.sma.manage.types.IpType;
import com.shds.sma.admin.entity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
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
    @Size(max = 20)
    @NotBlank
    private IpType ipType;

    @Column(length = 200, columnDefinition = "VARCHAR(200) COMMENT 'IP 주소'", nullable = false)
    @Size(max = 200)
    @NotBlank
    private String ipAddr;

//    @OneToOne
//    @JoinColumn(name = "ROLE_ID")
//    private Role role;

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

}
