package com.nci.webapp.AlzApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter  @Setter //<<---- seting getters and setter via lombock dependency to make the code clear
@AllArgsConstructor  @NoArgsConstructor //<<---- seting constructor via lombock dependency
@Entity
@Table(name = "side_effects")
public class SideEffects {

    @Id
    private int id;
    private String drug;
    private String arguments;

}
