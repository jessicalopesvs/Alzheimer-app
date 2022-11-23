package com.nci.webapp.AlzApp.model;

import java.util.stream.Stream;

public enum Emotions {
    TRUST("Trust"),
    DISGUST("Disgust"),
    FEAR("Fear"),
    SADNESS("Sadness"),
    ANTICIPATION("Anticipation"),
    JOY("Joy"),
    SURPRISE("Surprise"),
    ANGER("Anger");


    private final String displayValue;

    private Emotions(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public static Stream<Emotions> stream(){
        return Stream.of(Emotions.values());
    }
}
