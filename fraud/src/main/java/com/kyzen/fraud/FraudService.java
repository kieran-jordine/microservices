package com.kyzen.fraud;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public record FraudService(FraudRepository repository) {

    public Fraud setFraud(long customerId, boolean isFraudulent) {
        Fraud fraud = repository.findByCustomerId(customerId)
                .orElse(new Fraud(customerId, isFraudulent, LocalDateTime.now()));
        fraud.setFraudster(isFraudulent);
        return repository.save(fraud);
    }

    public Object isFraudulentCustomer(long customerId) {
        return repository.findByCustomerId(customerId); //.map(Fraud::isFraudster).orElse(false);
    }

    public List<Fraud> getFrauds() {
        return repository.findAll();
    }
}
