package com.kyzen.fraud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/fraud")
public record FraudController(FraudService fraudService, FraudRepository repo) {

    @PostMapping("/{customerId}/{isFraudulent}")
    public Fraud save(@PathVariable long customerId, @PathVariable boolean isFraudulent) {
        return fraudService.setFraud(customerId, isFraudulent);
    }

    @GetMapping("/{customerId}")
    public Object check(@PathVariable long customerId) {
        return fraudService.isFraudulentCustomer(customerId);
    }

    @GetMapping
    public List<Fraud> getFraud() {
        return fraudService.getFrauds();
    }

}
