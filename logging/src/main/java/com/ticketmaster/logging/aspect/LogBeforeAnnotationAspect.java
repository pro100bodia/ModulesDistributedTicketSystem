package com.ticketmaster.logging.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogBeforeAnnotationAspect {
//    private static final Logger LOG = Logger.getLogger(LogBeforeAnnotationAspect.class);

//    @Before("@annotation(com.ticketmaster.logging.annotation.LogBefore)")
//    public void logBefore(JoinPoint joinPoint){
////        LOG.info(String.format("%s received arguments: %s", joinPoint.getSignature(), joinPoint.getArgs()));
//        System.out.println(String.format("###############################################%s received arguments: %s", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs())));
//    }

    @Before("@annotation(com.ticketmaster.logging.annotation.LogBefore)")
    public void logBefore() {
        System.out.println("################################Log Before");
    }

}
