package com.irunninglog.graphql.object;

public abstract class AbstractShoe {

    private final String id;
    private final String name;
    private final boolean primary;

    AbstractShoe(String id, String name, boolean primary) {
        this.id = id;
        this.name = name;
        this.primary = primary;
    }

    public final String getId() {
        return id;
    }

    public final String getName() {
        return name;
    }

    public final boolean isPrimary() {
        return primary;
    }

}
