package com.kyzen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingGlobalFilter implements GlobalFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        LOGGER.info("PRE-REQUEST GLOBAL");
//        System.out.println("Gateway2 global pre");
        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
//                    LOGGER.info("POST-REQUEST GLOBAL");
//                    System.out.println("Gateway2 global post");
                }));
    }
}
