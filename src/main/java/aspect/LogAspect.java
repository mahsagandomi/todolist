package aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * Aspect class for logging the execution of service and web methods.
 * It logs method start, end, execution time, and exceptions using Log4j2.
 */
@Component
@Aspect
public class LogAspect {

    /**
     * Logger instance from Log4j2 for logging messages.
     */
    private final Logger log4j = LogManager.getLogger(LogAspect.class);

    /**
     * Around advice to log the execution of methods in web and service packages.
     * Logs the method start, execution time, successful completion, and exceptions.
     *
     * @param joinPoint the join point representing the method being executed
     * @return the result of the method execution
     * @throws Throwable if the intercepted method throws an exception
     */
    @Around("execution(* web.*.*(..)) || execution(* service.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        // Create a stopwatch to measure execution time
        StopWatch stopWatch = new StopWatch();
        Object result;

        try {
            // Log method start
            log4j.info(joinPoint.getSignature().getName() + " started");

            // Start measuring time
            stopWatch.start();

            // Proceed with the method execution
            result = joinPoint.proceed();

        } catch (Throwable e) {
            // Stop the stopwatch if an exception occurs
            stopWatch.stop();

            // Log the exception message
            log4j.error(e.getMessage());

            // Rethrow the exception to propagate it
            throw e;
        }

        // Stop the stopwatch after successful execution
        stopWatch.stop();

        // Log method completion and execution time in milliseconds
        log4j.info(joinPoint.getSignature().getName() + " run successfully in " +
                stopWatch.getTotalTime(TimeUnit.MILLISECONDS) + " ms");

        // Return the result of the method
        return result;
    }
}
