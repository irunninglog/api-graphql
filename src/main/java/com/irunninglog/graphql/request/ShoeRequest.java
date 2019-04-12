package com.irunninglog.graphql.request;

import com.irunninglog.graphql.object.ShoeDetail;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Component
public class ShoeRequest extends AbstractRequest<ShoeDetail> {

    ShoeRequest(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public ShoeDetail shoe(HttpServletRequest request, String id) {
        return get("https://www.strava.com/api/v3/gear/" + id, request, ShoeDetail.class);
    }

}
