package com.irunninglog.graphql.object;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class ShoeSummary extends AbstractShoe {

    @JsonCreator
    public ShoeSummary(@JsonProperty("id") String id,
                       @JsonProperty("name") String name,
                       @JsonProperty("primary") boolean primary) {

        super(id, name, primary);
    }

}
