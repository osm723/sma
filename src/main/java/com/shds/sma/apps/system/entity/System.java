package com.shds.sma.apps.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shds.sma.apps.system.dto.SystemModRequestDto;
import com.shds.sma.apps.admin.member.entity.Member;
import com.shds.sma.common.entity.BaseEntity;
import com.shds.sma.apps.cert.entity.Cert;
import com.shds.sma.apps.ip.entity.Ip;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SMA_SYSTEM")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class System extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "SYSTEM_ID")
    private Long id;

    @Column(length = 40, columnDefinition = "VARCHAR(40) COMMENT '시스템명'", nullable = false)
    private String systemName;

    @OneToMany(mappedBy = "system")
    @JsonIgnore
    private List<Member> systemManagers = new ArrayList<>();

    @Column(length = 2, columnDefinition = "INT COMMENT 'IP 만료 전 알림 일자'")
    private Integer preIpAlarm;

    @Column(length = 2, columnDefinition = "INT COMMENT '인증서 만료 전 알림 일자'")
    private Integer preCertAlarm;

    @Column(length = 400, columnDefinition = "VARCHAR(400) COMMENT '시스템 정보'")
    private String systemInfo;

    @OneToMany(mappedBy = "applySystem")
    @JsonIgnore
    private List<Ip> ips = new ArrayList<>();

    @OneToMany(mappedBy = "applySystem")
    @JsonIgnore
    private List<Cert> certs = new ArrayList<>();

    public void systemModified(SystemModRequestDto systemModRequestDto) {
        this.systemName = systemModRequestDto.getSystemName();
        //this.systemManagers = systemModRequestDto.getSystemManagers();
        this.preIpAlarm = systemModRequestDto.getPreIpAlarm();
        this.preCertAlarm = systemModRequestDto.getPreCertAlarm();
        this.systemInfo = systemModRequestDto.getSystemInfo();
    }

    @Builder
    public System(String systemName, List<Member> systemManagers, Integer preIpAlarm, Integer preCertAlarm, String systemInfo, List<Ip> ips, List<Cert> certs) {
        this.systemName = systemName;
        this.systemManagers = systemManagers;
        this.preIpAlarm = preIpAlarm;
        this.preCertAlarm = preCertAlarm;
        this.systemInfo = systemInfo;
        this.ips = ips;
        this.certs = certs;
    }
}
