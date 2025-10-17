package org.training.microservice.tc.msorder.integration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class MyCallee implements CommandLineRunner {
    private final MyCaller               myCaller;
    private final RetryRegistry          retryRegistry;
    private final CircuitBreakerRegistry circuitBreakerRegistry;


    @Override
    public void run(final String... args) throws Exception {
        Retry         retryLoc   = retryRegistry.retry("ca-retry2");
        Retry.Metrics metricsLoc = retryLoc.getMetrics();

        CircuitBreaker circuitBreakerLoc = circuitBreakerRegistry.circuitBreaker("my-cb");
        circuitBreakerLoc.getEventPublisher()
                         .onStateTransition(ec -> System.out.println(" circuit : " + ec));

        retryLoc.getEventPublisher()
                .onRetry(ec -> System.out.println("Retry Event : " + ec));

        for (int i = 0; i < 100; i++) {

            String methodLoc = null;
            try {
                Thread.sleep(500);
                methodLoc = myCaller.method("osman" + i);
            } catch (Exception exp) {
                System.out.println("Exp : " + exp.getClass()
                                                 .getName());
            }

            CircuitBreaker.Metrics metricsLoc1 = circuitBreakerLoc.getMetrics();
            System.out.println("Result : " + methodLoc
                               + " retry t : " + metricsLoc.getNumberOfTotalCalls()
                               + " f : " + metricsLoc.getNumberOfFailedCallsWithRetryAttempt()
                               + " s : " + metricsLoc.getNumberOfSuccessfulCallsWithoutRetryAttempt()
                               + " sw : " + metricsLoc.getNumberOfSuccessfulCallsWithRetryAttempt()
            );
            System.out.println("Result : " + methodLoc
                               + " state : " + circuitBreakerLoc.getState()
                               + " cb s : " + metricsLoc1.getNumberOfSuccessfulCalls()
                               + " f : " + metricsLoc1.getNumberOfFailedCalls()
                               + " np : " + metricsLoc1.getNumberOfNotPermittedCalls()
                               + " slow : " + metricsLoc1.getNumberOfSlowCalls()
            );
        }
        //throw new IllegalStateException("bitir");
    }
}
