package com.irunninglog.graphql.object;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class ShoeSummary {

    private final String id;
    private final String name;
    private final boolean primary;

    @JsonCreator
    public ShoeSummary(@JsonProperty("id") String id,
                       @JsonProperty("name") String name,
                       @JsonProperty("primary") boolean primary) {

        this.id = id;
        this.name = name;
        this.primary = primary;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isPrimary() {
        return primary;
    }

}
