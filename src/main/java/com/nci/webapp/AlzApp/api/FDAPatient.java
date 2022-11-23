package com.nci.webapp.AlzApp.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FDAPatient {
    @JsonProperty("reaction")
    List<FDAReaction> reactions;

    @Override
    public String toString() {
        return "FDAPatient{" +
                "reactions=" + reactions +
                '}';
    }
}
