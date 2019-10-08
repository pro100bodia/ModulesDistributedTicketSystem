package com.ticketmaster.logging.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogBeforeAnnotationAspect {
    private static final Logger LOG = Logger.getLogger(LogBeforeAnnotationAspect.class);

    @Before("@annotation(com.ticketmaster.logging.annotation.LogBefore)")
    public void logBefore(JoinPoint joinPoint) {
        LOG.info("method occured");
        LOG.info(String.format("%s received arguments: %s", joinPoint.getSignature()/*.toLongString()*/,
                Arrays.toString(joinPoint.getArgs())));
    }
}
