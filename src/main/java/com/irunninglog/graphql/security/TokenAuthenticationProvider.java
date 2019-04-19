package com.irunninglog.graphql.security;

import com.irunninglog.graphql.object.Athlete;
import com.irunninglog.graphql.request.AthleteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private final AthleteRequest request;

    @Autowired
    public TokenAuthenticationProvider(AthleteRequest request) {
        this.request = request;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null || authentication.getCredentials().toString().isBlank()) {
            return null;
        }

        try {
            Athlete athlete = request.athlete(authentication.getCredentials().toString());
            return new PreAuthenticatedAuthenticationToken(athlete.getId(), authentication.getCredentials());
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(PreAuthenticatedAuthenticationToken.class);
    }

}
