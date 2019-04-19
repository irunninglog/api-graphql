package com.irunninglog.graphql.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class BearerTokenAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        String bearerToken = ((HttpServletRequest) servletRequest).getHeader(AUTHORIZATION);

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            SecurityContextHolder.getContext().setAuthentication(new PreAuthenticatedAuthenticationToken(null, bearerToken));
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
