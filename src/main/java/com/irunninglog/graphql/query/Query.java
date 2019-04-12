package com.irunninglog.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.irunninglog.graphql.object.Athlete;
import com.irunninglog.graphql.object.ShoeDetail;
import com.irunninglog.graphql.request.AthleteRequest;
import com.irunninglog.graphql.request.ShoeRequest;
import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@SuppressWarnings("WeakerAccess")
public final class Query implements GraphQLQueryResolver {

    private final AthleteRequest athleteRequest;
    private final ShoeRequest shoeRequest;

    @Autowired
    public Query(AthleteRequest athleteRequest, ShoeRequest shoeRequest) {
        this.athleteRequest = athleteRequest;
        this.shoeRequest = shoeRequest;
    }

    public Athlete athlete(DataFetchingEnvironment environment) {
        GraphQLContext context = environment.getContext();
        HttpServletRequest httpServletRequest = context.getHttpServletRequest().isPresent()
                ? context.getHttpServletRequest().get() : null;

        return athleteRequest.athlete(Objects.requireNonNull(httpServletRequest));
    }

    public List<ShoeDetail> shoes(List<String> ids, DataFetchingEnvironment environment) {
        GraphQLContext context = environment.getContext();
        HttpServletRequest httpServletRequest = context.getHttpServletRequest().isPresent()
                ? context.getHttpServletRequest().get() : null;

        List<ShoeDetail> details = new ArrayList<>(ids.size());

        for (String id : ids) {
            details.add(shoeRequest.shoe(Objects.requireNonNull(httpServletRequest), id));
        }

        return details;
    }

}
