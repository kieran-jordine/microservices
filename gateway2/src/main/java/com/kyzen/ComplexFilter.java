package com.kyzen;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
public class ComplexFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //[
        // host:"localhost:9192",
        // sec-fetch-site:"none",
        // connection:"keep-alive",
        // upgrade-insecure-requests:"1",
        // sec-fetch-mode:"navigate",
        // accept:"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
        // user-agent:"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.4 Safari/605.1.15",
        // accept-language:"en-US,en;q=0.9",
        // sec-fetch-dest:"document",
        // accept-encoding:"gzip, deflate"
        //]

        System.out.println(exchange.getRequest().getId());
        System.out.println(exchange.getRequest().getPath());
        System.out.println(exchange.getRequest().getQueryParams());
        System.out.println(exchange.getRequest().getHeaders().getAcceptLanguage());

        String queryParamLocale = exchange.getRequest()
                .getQueryParams()
                .getFirst("locale");
        Locale locale = Optional.ofNullable(queryParamLocale)
                .map(Locale::forLanguageTag)
                .orElse(Locale.JAPAN);
        System.out.println(queryParamLocale +" => "+ locale);

        exchange.getRequest().mutate()
                .headers(h -> h.setAcceptLanguageAsLocales(List.of(locale)));
        System.out.println(exchange.getRequest().getHeaders().getAcceptLanguage());

        ServerWebExchange newExchange = exchange.mutate()
                .request(req -> exchange.getRequest())
                .build();
        URI uri = UriComponentsBuilder.fromUri(exchange.getRequest().getURI())
                .replacePath("")
                .build()
                .toUri();

        return chain.filter(exchange);
    }

}
