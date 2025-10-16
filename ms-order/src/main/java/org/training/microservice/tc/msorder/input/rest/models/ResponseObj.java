package org.training.microservice.tc.msorder.input.rest.models;

import lombok.Data;

@Data
public class ResponseObj<T> {
    private String error;
    private boolean errorOccurred;
    private T data;
}
