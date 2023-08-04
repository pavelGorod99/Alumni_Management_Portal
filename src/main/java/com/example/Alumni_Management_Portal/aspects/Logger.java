package com.example.Alumni_Management_Portal.aspects;

import com.example.Alumni_Management_Portal.dto.JobDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class Logger {

    @After("execution(* com.example.Alumni_Management_Portal.services.implementation.JobServiceImpl.create(..))")
    public void after(JoinPoint joinPoint) {
        System.out.println("After: " + joinPoint.getSignature().getDeclaringTypeName() + " : " + joinPoint.getSignature().getName() + " " + joinPoint.getArgs());
        JobDto jobDto = (JobDto) joinPoint.getArgs()[0];
        System.out.println(jobDto.getCompany());
        System.out.println(jobDto.getDescription());
    }

    private void writeLogIntoTheFile(String logActivity) {

        // USER ID _ CREATE LOG DATE (SSMMHHMMYYYY)
    }
}
