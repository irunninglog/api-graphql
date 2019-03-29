package com.irunninglog.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.irunninglog.graphql.object.Athlete;
import com.irunninglog.graphql.request.AthleteRequest;
import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
public final class AthleteQuery implements GraphQLQueryResolver {

    private final AthleteRequest request;

    @Autowired
    public AthleteQuery(AthleteRequest request) {
        this.request = request;
    }

    @SuppressWarnings("WeakerAccess")
    public Athlete athlete(DataFetchingEnvironment environment) {
        GraphQLContext context = environment.getContext();
        HttpServletRequest httpServletRequest = context.getHttpServletRequest().isPresent()
                ? context.getHttpServletRequest().get() : null;

        return request.athlete(Objects.requireNonNull(httpServletRequest));
    }

}
