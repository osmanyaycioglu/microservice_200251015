package org.training.microservice.tc.msorder.services;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.training.microservice.tc.msorder.integration.CustomerAccountIntegration;
import org.training.microservice.tc.msorder.services.models.Order;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderProcessService {
    private final CustomerAccountIntegration     customerAccountIntegration;
    private final KafkaTemplate<Integer, String> kafkaTemplate;


    public String place(Order orderParam) {
        kafkaTemplate.send("my-topic",
                           "Order received : " + orderParam.getCustomerName());
        return customerAccountIntegration.sendPaymentRequest(orderParam,
                                                             new BigDecimal(1_000));
    }


}
