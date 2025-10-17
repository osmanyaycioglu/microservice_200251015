package org.training.microservice.tc.msorder.integration;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.training.microservice.tc.msorder.integration.models.PaymentRequest;
import org.training.microservice.tc.msorder.integration.models.PaymentResponse;
import org.training.microservice.tc.msorder.services.models.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class CustomerAccountIntegration {
    private final RestTemplate       restTemplate;
    private final EurekaClient       eurekaClient;
    private final RestClient.Builder restClientBuilder;

    private AtomicLong index = new AtomicLong();

    @Retry(name = "ca-retry")
    public String sendPaymentRequest(Order orderParam,
                                     BigDecimal amount) {
        PaymentRequest paymentRequestLoc = new PaymentRequest();
        paymentRequestLoc.setCustomerId("customer1");
        paymentRequestLoc.setCustomerName(orderParam.getCustomerName());
        paymentRequestLoc.setAmount(amount);
        paymentRequestLoc.setOrderId(orderParam.getOrderId());

        ResponseEntity<PaymentResponse> entityLoc = restClientBuilder.build()
                                                                     .post()
                                                                     .uri("http://MS-CUSTOMER-ACCOUNT/api/v1/customer/payment/pay")
                                                                     .contentType(MediaType.APPLICATION_JSON)
                                                                     .body(paymentRequestLoc)
                                                                     .retrieve()
                                                                     .toEntity(PaymentResponse.class);

        return entityLoc.getBody().getTransactionId();
    }

    public String sendPaymentRequest3(Order orderParam,
                                      BigDecimal amount) {
        PaymentRequest paymentRequestLoc = new PaymentRequest();
        paymentRequestLoc.setCustomerId("customer1");
        paymentRequestLoc.setCustomerName(orderParam.getCustomerName());
        paymentRequestLoc.setAmount(amount);
        paymentRequestLoc.setOrderId(orderParam.getOrderId());
        Application        applicationLoc  = eurekaClient.getApplication("MS-CUSTOMER-ACCOUNT");
        List<InstanceInfo> instancesLoc    = applicationLoc.getInstances();
        long               currentIndex    = index.getAndIncrement() % instancesLoc.size();
        InstanceInfo       instanceInfoLoc = instancesLoc.get((int) currentIndex);
        RestTemplate       restTemplateLoc = new RestTemplate();

        PaymentResponse paymentResponseLoc = restTemplateLoc.postForObject("http://"
                                                                           + instanceInfoLoc.getIPAddr()
                                                                           + ":"
                                                                           + instanceInfoLoc.getPort()
                                                                           + "/api/v1/customer/payment/pay",
                                                                           paymentRequestLoc,
                                                                           PaymentResponse.class);
        return paymentResponseLoc.getTransactionId();
    }

    public String sendPaymentRequest2(Order orderParam,
                                      BigDecimal amount) {
        PaymentRequest paymentRequestLoc = new PaymentRequest();
        paymentRequestLoc.setCustomerId("customer1");
        paymentRequestLoc.setCustomerName(orderParam.getCustomerName());
        paymentRequestLoc.setAmount(amount);
        paymentRequestLoc.setOrderId(orderParam.getOrderId());
        PaymentResponse paymentResponseLoc = restTemplate.postForObject("http://MS-CUSTOMER-ACCOUNT/api/v1/customer/payment/pay",
                                                                        paymentRequestLoc,
                                                                        PaymentResponse.class);
        return paymentResponseLoc.getTransactionId();
    }

}
