package org.training.microservice.tc.msorder.input.rest.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderItem {
    @NotBlank
    private String mealName;
    @NotBlank
    private String mealId;
    @Positive
    private Double amount;
}
