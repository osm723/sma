package com.shds.sma.admin.entity;

import com.shds.sma.admin.dto.system.SystemModRequestDto;
import com.shds.sma.common.entity.BaseEntity;
import com.shds.sma.member.entity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Entity
@Table(name = "SMA_SYSTEM")
@Getter
public class System extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "SYSTEM_ID")
    private Long id;

    @Column(length = 40, columnDefinition = "VARCHAR(40) COMMENT '시스템명'", nullable = false)
    @NotBlank
    private String systemName;

    @OneToMany(mappedBy = "system")
    //@NotBlank
    private List<Member> systemManagers;

    @Column(length = 2, columnDefinition = "INT COMMENT 'IP 만료 전 알림 일자'")
    @Min(1)
    @Max(99)
    private Integer preIpAlarm;

    @Column(length = 2, columnDefinition = "INT COMMENT '인증서 만료 전 알림 일자'")
    @Min(1)
    @Max(99)
    private Integer preCertAlarm;

    public void systemModified(SystemModRequestDto systemModRequestDto) {
        this.systemName = systemModRequestDto.getSystemName();
        this.systemManagers = systemModRequestDto.getSystemManagers();
        this.preIpAlarm = systemModRequestDto.getPreIpAlarm();
        this.preCertAlarm = systemModRequestDto.getPreCertAlarm();
    }
}
