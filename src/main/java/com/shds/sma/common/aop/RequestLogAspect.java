package com.shds.sma.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class RequestLogAspect {

    @Before("execution(* com.shds.sma.external.api.controller..*(..))")
    public void addApiLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        log.info("======== API 요청 ======== {} : {}",signature.getDeclaringTypeName(), signature.getName());
    }

    @Before("execution(* com.shds.sma.batch.scheduler..*(..))")
    public void addBatchLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        log.info("======== Batch 요청 ======== {} : {}",signature.getDeclaringTypeName(), signature.getName());
    }
}
