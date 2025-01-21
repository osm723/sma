package com.shds.sma.manage.entity;

import com.shds.sma.common.entity.BaseEntity;
import com.shds.sma.common.entity.System;
import com.shds.sma.manage.types.CertType;
import com.shds.sma.manage.types.IpType;
import com.shds.sma.member.entity.Member;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "SMA_CERT")
public class Cert extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "CERT_ID")
    private Long id;

    private CertType certType;

    private String certName;

    @OneToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @OneToOne
    @JoinColumn(name = "SYSTEM_ID")
    private System system;

    @Column(length = 400, columnDefinition = "VARCHAR(400) COMMENT '내용'")
    private String content;

    @Column(length = 400, columnDefinition = "VARCHAR(400) COMMENT '사이트주소'")
    private String siteLink;

    @Column(length = 14, columnDefinition = "VARCHAR(12) COMMENT '시작일자'")
    private LocalDate startDate;

    @Column(length = 14, columnDefinition = "VARCHAR(12) COMMENT '종료일자'")
    private LocalDate endDate;

    @OneToOne
    @JoinColumn(name = "APPROVAL_ID")
    private Approval approval;

}
