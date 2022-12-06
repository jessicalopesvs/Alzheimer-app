package com.nci.webapp.AlzApp.model;

import java.util.stream.Stream;

public enum RolesType {
    USER("USER"),
    ADMIN("ADMIN");

    private final String value;

    private RolesType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Stream<RolesType> stream() {
        return Stream.of(RolesType.values());
    }
}
