package com.sagar.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sagar.PaymentRequest.PaymentRequest;
import com.sagar.ResponseDto.PaymentResponse;
import com.sagar.service.RoutingService;

import reactor.core.publisher.Mono;

@RestController
public class PaymentController {

    @Autowired
    private RoutingService routingService;

    @PostMapping(value = "/process-payment", consumes = {"application/json"})
    public Mono<PaymentResponse> processPayment(@RequestBody PaymentRequest request) {
        return routingService.routePayment(request);
    }
}

