package com.nci.webapp.AlzApp.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class FDADrugs {
    @JsonProperty("results")
    List<FDAResult> results;

    public List<FDAResult> getResults() {
        return results;
    }

    public List<String> toReactions() {
        List<String> reactions = new ArrayList<>();
        results.stream().forEach(result -> {
            result.patient.reactions.forEach(reaction -> {
                reactions.add(reaction.name);
            });
        });
        return reactions;
    }

    @Override
    public String toString() {
        return "FDADrugs{" +
                "results=" + results +
                '}';
    }
}
