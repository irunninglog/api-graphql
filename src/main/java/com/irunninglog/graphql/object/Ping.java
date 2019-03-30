package com.irunninglog.graphql.object;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public final class Ping {

    private final String timestamp;

    public Ping() {
        timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public String getTimestamp() {
        return timestamp;
    }

}
