package org.training.microservice.tc.msorder.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.training.microservice.tc.msorder.integration.CustomerAccountIntegration;
import org.training.microservice.tc.msorder.services.models.Order;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderProcessService {
    private final CustomerAccountIntegration customerAccountIntegration;

    public String place(Order orderParam) {
        return customerAccountIntegration.sendPaymentRequest(orderParam,
                                                             new BigDecimal(1_000));
    }


}
