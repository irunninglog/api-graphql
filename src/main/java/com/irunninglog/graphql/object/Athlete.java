package com.irunninglog.graphql.object;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class Athlete {

    public enum Sex {
        @JsonProperty("M")
        Male,
        @JsonProperty("F")
        Female
    }

    private final String id;
    private final String firstName;
    private final String lastName;
    private final String city;
    private final String state;
    private final String country;
    private final Sex sex;
    private final String profileSmall;
    private final String profileLarge;
    private final List<ShoeSummary> shoeSummaries;

    @JsonCreator
    public Athlete(@JsonProperty("id") String id,
                   @JsonProperty("firstname") String firstName,
                   @JsonProperty("lastname") String lastName,
                   @JsonProperty("city") String city,
                   @JsonProperty("state") String state,
                   @JsonProperty("country") String country,
                   @JsonProperty("sex") Sex sex,
                   @JsonProperty("profile_medium") String profileSmall,
                   @JsonProperty("profile") String profileLarge,
                   @JsonProperty("shoes") List<ShoeSummary> shoeSummaries) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.sex = sex;
        this.profileSmall = profileSmall;
        this.profileLarge = profileLarge;
        this.shoeSummaries = shoeSummaries;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public Sex getSex() {
        return sex;
    }

    public String getProfileSmall() {
        return profileSmall;
    }

    public String getProfileLarge() {
        return profileLarge;
    }

    public List<ShoeSummary> getShoeSummaries() {
        return shoeSummaries;
    }

}
