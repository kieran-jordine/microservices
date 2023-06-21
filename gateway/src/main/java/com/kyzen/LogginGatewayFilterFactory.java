package com.kyzen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LogginGatewayFilterFactory extends AbstractGatewayFilterFactory<LogginGatewayFilterFactory.Config> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogginGatewayFilterFactory.class);

    public LogginGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            LOGGER.info("LOOOOOGGGGGGGIIIIIINNNNNNGGGGG!!!!!");
            return chain.filter(exchange);
        });
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of("baseMessage", "preLogger", "postLogger");
    }

    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean PostLogger;

        public Config() {
        }

        public Config(String baseMessage, boolean preLogger, boolean postLogger) {
            this.baseMessage = baseMessage;
            this.preLogger = preLogger;
            PostLogger = postLogger;
        }

        public String getBaseMessage() {
            return baseMessage;
        }

        public void setBaseMessage(String baseMessage) {
            this.baseMessage = baseMessage;
        }

        public boolean isPreLogger() {
            return preLogger;
        }

        public void setPreLogger(boolean preLogger) {
            this.preLogger = preLogger;
        }

        public boolean isPostLogger() {
            return PostLogger;
        }

        public void setPostLogger(boolean postLogger) {
            PostLogger = postLogger;
        }
    }

}
