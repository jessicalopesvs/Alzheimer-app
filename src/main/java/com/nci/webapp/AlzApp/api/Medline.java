package com.nci.webapp.AlzApp.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Medline {

    private String ghr_page;
    private String name;
    @JsonProperty ("text-list")
    private List<MedlineText> texts;

    @JsonProperty("synonym-list")
    private List<Synonym> synonyms;


    @NoArgsConstructor
   static class MedlineText {
        @JsonProperty("text")
        MedlineHtml text;


        @Override
        public String toString() {
            return text.toString();
        }
    }

    @NoArgsConstructor
    static class MedlineHtml {
        @JsonProperty("html")
        String html;

        @Override
        public String toString() {
            return html;
        }
    }

    static class Synonym{
        @JsonProperty("synonym")

        String synonym;

        @Override
        public String toString() {
            return synonym;
        }
    }
}
