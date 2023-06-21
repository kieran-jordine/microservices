package com.kyzen.customer;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public record CustomerService(CustomerRepository repository, FraudClient fraudClient) {

    public Customer register(Customer customer) {
        customer = repository.save(customer);
        fraudClient.set(customer.getId(), false);
        return customer;
    }

    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    public Customer getCustomer(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }
}
