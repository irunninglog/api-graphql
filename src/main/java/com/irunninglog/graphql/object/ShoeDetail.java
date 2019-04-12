package com.irunninglog.graphql.object;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class ShoeDetail extends AbstractShoe {

    private final float distance;
    private final String brandName;
    private final String modelName;
    private final String description;

    @JsonCreator
    public ShoeDetail(@JsonProperty("id") String id,
                      @JsonProperty("name") String name,
                      @JsonProperty("primary") boolean primary,
                      @JsonProperty("distance") float distance,
                      @JsonProperty("brand_name") String brandName,
                      @JsonProperty("model_name") String modelName,
                      @JsonProperty("description") String description) {

        super(id, name, primary);
        this.distance = distance;
        this.brandName = brandName;
        this.modelName = modelName;
        this.description = description;
    }

    public float getDistance() {
        return distance;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public String getDescription() {
        return description;
    }

}
