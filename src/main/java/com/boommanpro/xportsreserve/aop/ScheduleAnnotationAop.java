package com.boommanpro.xportsreserve.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Slf4j
@Aspect
@Component
public class ScheduleAnnotationAop {

    @Around("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public Object logCronExecute(ProceedingJoinPoint joinPoint) throws Throwable {
        String name = joinPoint.getSignature().getName();
        log.info("{} cron task execute start", name);
        Object proceed = joinPoint.proceed();
        log.info("{} cron task execute end", name);
        return proceed;
    }
}
