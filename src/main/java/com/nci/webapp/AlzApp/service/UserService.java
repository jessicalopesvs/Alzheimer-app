package com.nci.webapp.AlzApp.service;

import com.nci.webapp.AlzApp.dto.RequestUser;
import com.nci.webapp.AlzApp.model.User;

import java.util.List;

public interface UserService {
    void saveUser(RequestUser userDto);

    User findByEmail(String email);

    List<RequestUser> findAllUsers();
}


