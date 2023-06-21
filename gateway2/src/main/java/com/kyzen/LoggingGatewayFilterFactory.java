package com.kyzen;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class LoggingGatewayFilterFactory extends AbstractGatewayFilterFactory<LoggingGatewayFilterFactory.Config> {

    public LoggingGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
//            System.out.println("Gateway2 "+this.getClass().getName()+" local pre");
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
//                        System.out.println("Gateway2 "+this.getClass().getName()+" local post");
                    }));
        });
    }

    public static class Config {
        public Config() {}
    }

}
