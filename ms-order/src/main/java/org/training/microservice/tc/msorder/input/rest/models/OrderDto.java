package org.training.microservice.tc.msorder.input.rest.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.training.microservice.tc.msorder.input.rest.validation.CheckWords;

import java.util.List;

@Data
public class OrderDto {
    @NotBlank
    @Size(min = 3)
    @CheckWords({"1234", "abc", "xyz"})
    private String customerName;
    @NotBlank
    @Size(min = 10,max = 11)
    @CheckWords({"123", "qwerty", "xyz","xcv"})
    private String customerNumber;
    @Valid
    private List<OrderItem> orderItem;
}
