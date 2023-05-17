package com.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;


@Component
public class MypreFilter implements GlobalFilter {

    Logger log = LoggerFactory.getLogger(MypreFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("At Prefilter method executed from Gateway :: ");
        //access request information
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        HttpHeaders httpHeaders = serverHttpRequest.getHeaders();
        Set<String> Keyset = httpHeaders.keySet();
        Keyset.forEach(key -> {
            List<String> values = httpHeaders.get(key);
            log.info(key + "::" + httpHeaders.values());
        });
        return chain.filter(exchange);
    }
}
