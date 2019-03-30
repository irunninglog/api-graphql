package com.irunninglog.graphql;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = "com.irunninglog.graphql")
public class TestConfig {

    @Bean
    public RestTemplate restTemplate() {
        return Mockito.mock(RestTemplate.class);
    }

}
