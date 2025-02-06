//package com.shds.sma.manage.entity;
//
//import com.shds.sma.common.entity.BaseEntity;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
//
//@Entity
//@Table(name = "SMA_ROLE")
//public class Role {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "ROLE_ID")
//    private Long id;
//
//    @Column(length = 100, columnDefinition = "VARCHAR(100) COMMENT '업무명'", nullable = false)
//    @Size(max = 100)
//    @NotBlank
//    private String roleName;
//
//    @Column(length = 400, columnDefinition = "VARCHAR(400) COMMENT '내용'")
//    @Size(max = 400)
//    private String content;
//
//}
