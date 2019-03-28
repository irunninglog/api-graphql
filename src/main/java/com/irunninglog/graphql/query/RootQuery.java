package com.irunninglog.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.irunninglog.graphql.object.Athlete;
import org.springframework.stereotype.Component;

@Component
public class RootQuery implements GraphQLQueryResolver {

    public Athlete athlete() {
        throw new UnsupportedOperationException("not supported:athlete");
    }

}
