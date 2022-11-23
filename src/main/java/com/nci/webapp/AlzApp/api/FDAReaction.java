package com.nci.webapp.AlzApp.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FDAReaction {
    @JsonProperty("reactionmeddrapt")
    String name;
    @JsonProperty("reactionoutcome")
    int outcome;

    @Override
    public String toString() {
        return "FDAReaction{" +
                "name='" + name + '\'' +
                ", outcome=" + outcome +
                '}';
    }
}
