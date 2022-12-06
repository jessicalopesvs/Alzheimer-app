package com.nci.webapp.AlzApp.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUser {
    //    private Long id;
    @NotEmpty
    private String fName;
    @NotEmpty
    private String lName;
    @NotEmpty
    private String username;
    @NotEmpty(message = "Set a password. The field should not be empty")
    private String password;
    @NotEmpty(message = "Email shoud not be empty")
    @Email
    private String email;
    private Boolean enabled = true;
}
