package com.shds.sma.common.entity;

import com.shds.sma.member.entity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "SMA_SYSTEM")
public class System extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "SYSTEM_ID")
    private Long id;

    @Column(length = 40, columnDefinition = "VARCHAR(40) COMMENT '시스템명'")
    @NotBlank
    private String systemName;

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    @NotBlank
    private Member systemManager;

    @Column(length = 2, columnDefinition = "INT COMMENT 'IP 만료 전 알림 일자'")
    @Min(1)
    @Max(99)
    private Integer preIpAlarm;

    @Column(length = 2, columnDefinition = "INT COMMENT '인증서 만료 전 알림 일자'")
    @Min(1)
    @Max(99)
    private Integer preCertAlarm;

}
