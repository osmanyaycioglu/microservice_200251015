package org.training.microservice.tc.msorder.services.models;

import lombok.Data;
import org.training.microservice.tc.msorder.input.rest.models.OrderItem;

import java.util.List;

@Data
public class Order {
    private String          orderId;
    private String          customerName;
    private String          customerNumber;
    private List<OrderItem> orderItem;
}
