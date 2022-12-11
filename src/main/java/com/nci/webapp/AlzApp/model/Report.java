package com.nci.webapp.AlzApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Getter  @Setter //<<---- seting getters and setter via lombock
@AllArgsConstructor  @NoArgsConstructor //<<---- seting constructor via lombock
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "UniqueUserAndDate", columnNames = {"date","user_username"})})
public class Report implements Serializable {

    //Variables

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private LocalDate date;
    private String drug;
    private int dayClassification;
    @Column(name="arguments")
    @ElementCollection(targetClass=String.class)
    private List<String> sideEffects = new ArrayList<>();

    @Column(name="emotionRating")
    @MapKeyColumn(name="emotion")
    @ElementCollection
    private Map<String,Integer> behaviour = new HashMap<>();


    @MapKeyColumn(name="symptoms")
    @Column(name="symptomRating")
    @ElementCollection
    private Map<String,Integer> symptom= new HashMap<>();

    @JsonBackReference
    @ManyToOne (fetch = FetchType.LAZY)
    private User user;


}
