package com.irunninglog.graphql.security;

import com.irunninglog.graphql.request.AthleteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // TODO - Eliminate this warning
    @Autowired
    private AthleteRequest athleteRequest;

    @Bean
    public BearerTokenAuthenticationFilter authFilter() {
        return new BearerTokenAuthenticationFilter();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(AthleteRequest request) {
        return new TokenAuthenticationProvider(request);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class)
                .cors().disable()
                .csrf().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers(GET, "ping").permitAll()
                .anyRequest().authenticated()
                .and()
                .authenticationProvider(authenticationProvider(athleteRequest))
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(UNAUTHORIZED));
    }

}
