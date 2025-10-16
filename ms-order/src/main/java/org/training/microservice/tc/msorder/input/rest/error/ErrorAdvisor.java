package org.training.microservice.tc.msorder.input.rest.error;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorAdvisor {
    private static final Logger logger = LoggerFactory.getLogger(ErrorAdvisor.class);

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handleError(IllegalArgumentException exp) {
        return ErrorObj.builder()
                       .withErrorCode(1001)
                       .withErrorDesc(exp.getMessage())
                       .withSubErrors(null)
                       .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handleError(MethodArgumentNotValidException exp) {
        return ErrorObj.builder()
                       .withErrorCode(1002)
                       .withErrorDesc("validation error")
                       .withSubErrors(exp.getAllErrors()
                                         .stream()
                                         .map(e -> ErrorObj.builder()
                                                           .withErrorCode(1003)
                                                           .withErrorDesc("" + e)
                                                           .withSubErrors(null)
                                                           .build())
                                         .toList())
                       .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorObj handleError(Exception exp) {
        logger.error("[ErrorAdvisor][handleError]-> *Error* : " + exp.getMessage(),exp);
        return ErrorObj.builder()
                       .withErrorCode(5001)
                       .withErrorDesc(exp.getMessage())
                       .withSubErrors(null)
                       .build();
    }

}
