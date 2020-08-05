package demo.projects.cucumber.test.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * Logging aspect.
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {

    /**
     * Advices cucumber step definition object methods before execution by logging step definition method call.
     * @param joinPoint Step definition method join point.
     */
    @Before("execution(* demo.projects.cucumber.step.def..*.*(..))")
    public void cucumberStepLogging(JoinPoint joinPoint) {
        String stepName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            log.info("Calling cucumber step: {}", stepName);
        } else {
            log.info("Calling cucumber step: {}, with params: {}", stepName, args);
        }
    }

    /**
     * Advices all methods under cucumber package by logging any exception thrown during test execution.
     * @param e Exception thrown by method join point.
     */
    @AfterThrowing(value = "execution(* demo.projects.cucumber..*.*(..))", throwing = "e")
    public void logExceptions(Throwable e) {
        log.error("Caught exception during test case execution", e);
    }
}
