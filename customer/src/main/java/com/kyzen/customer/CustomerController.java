package com.kyzen.customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService customerService) {

    @PostMapping
    public Customer register(@RequestBody Customer customer) {
        return customerService.register(customer);
    }

    @GetMapping
    public List<Customer> getCustomers() {
        customerService.register(new Customer("me", "you", "email"));
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable long id) {
        return customerService.getCustomer(id);
    }

}
