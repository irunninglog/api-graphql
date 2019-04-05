package com.irunninglog.graphql.object;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public final class Ping {

    @JsonProperty("time")
    private final String timestamp;

    public Ping() {
        timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public String getTimestamp() {
        return timestamp;
    }

}
