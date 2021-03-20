package com.exp.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class WebClientUtil extends BaseUtil<Mono<Integer>> {

    @Autowired
    private WebClient webClient;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                        .clientConnector(new ReactorClientHttpConnector())
                        .build();
    }

    @Override
    public Mono<Integer> getData(int delay) {
        return webClient.get()
                        .uri("http://localhost:" + serverPort + "data?delay=" + delay)
                        .retrieve()
                        .bodyToMono(Integer.class);
    }

}
