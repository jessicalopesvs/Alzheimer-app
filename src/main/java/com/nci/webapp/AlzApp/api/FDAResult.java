package com.nci.webapp.AlzApp.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FDAResult {
    @JsonProperty("patient")
    FDAPatient patient;

    @Override
    public String toString() {
        return "FDAResult{" +
                "patient=" + patient +
                '}';
    }
}
