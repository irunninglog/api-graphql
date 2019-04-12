package com.irunninglog.graphql.request;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

abstract class AbstractRequest<T> {

    private final RestTemplate restTemplate;

    AbstractRequest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    T get(String url, HttpServletRequest request, Class<T> clazz) {
        String authHeader = request.getHeader(AUTHORIZATION);

        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTHORIZATION, authHeader);

        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, clazz);

        return responseEntity.getBody();
    }

}
