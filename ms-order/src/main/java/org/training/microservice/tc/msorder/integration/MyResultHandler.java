package org.training.microservice.tc.msorder.integration;

import java.util.function.Predicate;

public class MyResultHandler implements Predicate<String> {

    @Override
    public boolean test(final String stringParam) {
        System.out.println("Result Predicate : " + stringParam);
        return false;
    }
}
