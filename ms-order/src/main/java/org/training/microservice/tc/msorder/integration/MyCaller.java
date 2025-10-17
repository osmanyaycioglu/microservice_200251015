package org.training.microservice.tc.msorder.integration;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Component;

@Component
public class MyCaller {

    private long counter = 0;

    // @Retry(name = "ca-retry2", fallbackMethod = "methodFallback")
    @CircuitBreaker(name = "my-cb",fallbackMethod = "methodFallback")
    public String method(String str) {
        counter++;
        System.out.println("Method called : " + str + " counter : " + counter);
        if (counter % 3 == 0) {
            System.out.println("throwing exception 1 : " + counter);
            throw new IllegalStateException("Counter : " + counter);
        }
//        if (counter % 5 == 0) {
//            System.out.println("throwing exception 2 : " + counter);
//            throw new IllegalStateException("Counter : " + counter);
//        }
        return "Hello " + str;
    }

    public String methodFallback(String str,
                                 Throwable throwableParam) {
        System.out.println("Fallback : " + counter);
        return "fallback : " + counter;
    }

}
