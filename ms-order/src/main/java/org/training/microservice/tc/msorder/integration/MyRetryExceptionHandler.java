package org.training.microservice.tc.msorder.integration;

import java.util.function.Predicate;

public class MyRetryExceptionHandler implements Predicate<Throwable> {

    @Override
    public boolean test(final Throwable throwableParam) {
        if (throwableParam instanceof IllegalStateException) {
            System.out.println("Retry predicate");
        }
        return true;
    }

}
