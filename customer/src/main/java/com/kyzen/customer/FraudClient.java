package com.kyzen.customer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public record FraudClient(@Qualifier("fraudRestTemplate") RestTemplate restTemplate) {

    public Object check(long customerId) {
        RequestEntity<Void> request = RequestEntity
                .method(HttpMethod.GET, "/{customerId}", customerId)
                .accept(MediaType.APPLICATION_JSON)
                .build();
        return restTemplate.exchange(request, Boolean.class);
    }

    public ResponseEntity<String> set(long customerId, boolean isFraudster) {
        RequestEntity<Void> request = RequestEntity
                .method(HttpMethod.POST, "/{customerId}/{isFraudulent}", customerId, isFraudster)
                .accept(MediaType.APPLICATION_JSON)
                .build();
        return restTemplate.exchange(request, String.class);
    }

}
