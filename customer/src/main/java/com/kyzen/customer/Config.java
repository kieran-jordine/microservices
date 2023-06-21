package com.kyzen.customer;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean(name = "fraudRestTemplate")
    @LoadBalanced // important for parsing uri to get to client
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
//                .rootUri("http://localhost:8082/api/v1/fraud")
                .rootUri("http://FRAUD/api/v1/fraud")
                .build();
    }
}
