package com.irunninglog.graphql.request;

import com.irunninglog.graphql.object.Athlete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public final class AthleteRequest {

    private final RestTemplate restTemplate;

    @Autowired
    public AthleteRequest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Athlete athlete(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION);

        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTHORIZATION, authHeader);

        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<Athlete> responseEntity = restTemplate.exchange("https://www.strava.com/api/v3/athlete", HttpMethod.GET, httpEntity, Athlete.class);

        return responseEntity.getBody();
    }

}
