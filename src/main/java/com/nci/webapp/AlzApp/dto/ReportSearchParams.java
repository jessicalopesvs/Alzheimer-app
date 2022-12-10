package com.nci.webapp.AlzApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ReportSearchParams implements Serializable {
    private String key;
    private String value;

    @Override
    public String toString() {
        return "ReportSearchParams{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
