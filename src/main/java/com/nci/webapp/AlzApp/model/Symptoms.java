package com.nci.webapp.AlzApp.model;

import java.util.stream.Stream;



public enum Symptoms {

    SOMNOLENCE("Somnolence"),
    WEAKNESS("Weakness"),
    NAUSEUS("Nauseus"),
    VOMIT("Vomit"),
    APPETITE("Appetite"),
    HEADACHE("Headache"),
    BODYACHE("Bodyache"),
    CONFUSIONAL_STATE("Confusional_state");


    private final String displayValue;

    private Symptoms(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public static Stream<Symptoms> stream(){
        return Stream.of(Symptoms.values());
    }
}


