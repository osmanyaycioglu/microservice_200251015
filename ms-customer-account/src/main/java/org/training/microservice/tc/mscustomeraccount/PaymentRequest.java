package org.training.microservice.tc.mscustomeraccount;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {

    private String orderId;
    private String customerId;
    private String customerName;
    private BigDecimal amount;

}
