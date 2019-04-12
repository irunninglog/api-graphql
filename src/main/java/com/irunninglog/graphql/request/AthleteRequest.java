package com.irunninglog.graphql.request;

import com.irunninglog.graphql.object.Athlete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Component
public class AthleteRequest extends AbstractRequest<Athlete> {

    @Autowired
    public AthleteRequest(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Cacheable(cacheNames = "athletes", key = "#a0.getHeader('Authorization')")
    public Athlete athlete(HttpServletRequest request) {
        return get("https://www.strava.com/api/v3/athlete", request);
    }

}
