package com.exp.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateUtil extends BaseUtil<Integer> {

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public Integer getData(int delay) {
        return restTemplate.getForEntity(String.format(URI_TEMPLATE, serverPort, delay), Integer.class)
                            .getBody();
    }

}
