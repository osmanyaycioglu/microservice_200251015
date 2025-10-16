package org.training.microservice.tc.msorder.input.rest.error;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ErrorObj {
    private List<ErrorObj> subErrors;
    private String         errorDesc;
    private Integer        errorCode;

    public ErrorObj() {
    }

    @Builder(setterPrefix = "with")
    public ErrorObj(final List<ErrorObj> subErrors,
                    final String errorDesc,
                    final Integer errorCode) {
        this.subErrors = subErrors;
        this.errorDesc = errorDesc;
        this.errorCode = errorCode;
    }

    public static void main(String[] args) {
        ErrorObj.builder()
                .withErrorCode(100)
                .withErrorDesc("abc")
                .withSubErrors(null)
                .build();
    }
}
