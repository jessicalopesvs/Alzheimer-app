package com.nci.webapp.AlzApp.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Report {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date date;
    private String drug;
    private int dayClassification;




}
