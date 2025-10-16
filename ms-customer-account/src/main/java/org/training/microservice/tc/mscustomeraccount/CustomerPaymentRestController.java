package org.training.microservice.tc.mscustomeraccount;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer/payment")
public class CustomerPaymentRestController {

    @Value("${server.port}")
    private Integer port;

    @PostMapping("/pay")
    public PaymentResponse pay(@RequestBody PaymentRequest paymentRequestParam) {
        return new PaymentResponse(paymentRequestParam.getOrderId(),
                                   UUID.randomUUID()
                                       .toString() + "/" + port);
    }

}
