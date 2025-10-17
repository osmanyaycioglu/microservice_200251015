package org.training.microservice.tc.msorder.input.rest;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.training.microservice.tc.msorder.input.rest.mappers.IObjectMapper;
import org.training.microservice.tc.msorder.input.rest.models.OrderDto;
import org.training.microservice.tc.msorder.input.rest.models.ResponseObj;
import org.training.microservice.tc.msorder.services.OrderProcessService;

//@Component
//@Controller
//@Service
//@Repository
//@Configuration


@RestController
@RequestMapping("/api/v1/order/process")
@RequiredArgsConstructor
public class OrderProcessRestController {
    private final OrderProcessService orderProcessService;

    @Value("${server.port}")
    private Integer localPort;

    //@RequestMapping(method = RequestMethod.POST,path = "/api/v1/order/process/place")
    @PostMapping("/place")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "hsgjagd", description = "dhsgchscjhsdbsjdchb")
    public ResponseEntity<String> place(@Valid @RequestBody OrderDto orderDtoParam) {

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                             .header("xyz",
                                     "abc")
                             .body(orderProcessService.place(IObjectMapper.OBJECT_MAPPER.toOrder(orderDtoParam))
                                   + "--"
                                   + localPort);
    }

    @GetMapping("/cancel")
    public String cancelOrder(@RequestParam String orderId) {
        return "OK";
    }

}
