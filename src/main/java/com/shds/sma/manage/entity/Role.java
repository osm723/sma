package com.shds.sma.manage.entity;

import com.shds.sma.common.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "SMA_ROLE")
public class Role extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "ROLE_ID")
    private Long id;

    @Column(length = 100, columnDefinition = "VARCHAR(100) COMMENT '업무명'")
    private String roleName;

    @Column(length = 400, columnDefinition = "VARCHAR(400) COMMENT '내용'")
    private String content;

}
