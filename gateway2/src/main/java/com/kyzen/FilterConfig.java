package com.kyzen;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    //@Bean
    public RouteLocator routes(RouteLocatorBuilder builder, LoggingBeanConfigFilter factory) {
        return builder.routes()
                .route("service_route", r -> r.path("/api/v1/customers/**")
                        .filters(f ->
                                f.filter(factory.apply(
                                        new LoggingBeanConfigFilter.Config())))
                        .uri("lb://CUSTOMER"))
                .build();
    }
}
