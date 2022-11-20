package com.nci.webapp.AlzApp.model;

public enum MoodList {
    TRUST("Trust"),
    DISGUST("Disgust"),
    FEAR("Fear"),
    SADNESS("Sadness"),
    ANTICIPATION("Anticipation"),
    JOY("Joy"),
    SURPRISE("Surprise"),
    ANGER("Anger");


    private final String displayValue;

    private MoodList (String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
