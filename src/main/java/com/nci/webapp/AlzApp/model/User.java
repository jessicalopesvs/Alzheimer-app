package com.nci.webapp.AlzApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter  @Setter //<<---- seting getters and setter via lombock dependency to make the code clear
@AllArgsConstructor  @NoArgsConstructor //<<---- seting constructor via lombock dependency
@Entity
@Table(name = "users")
public class User {

    @Id
    private String username;
    @JsonIgnore
    private String fName;
    @JsonIgnore
    private String lName;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private Boolean enabled = true;

    @JsonIgnore
    @Column(nullable = false, unique = true)
    private String email;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)

    private List<Report> reports;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "username") },
            inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }
    )
    @JsonIgnore
    private List<Role> roles = new ArrayList<>();


}
