package com.nci.webapp.AlzApp.repository;

import com.nci.webapp.AlzApp.model.Report;
import com.nci.webapp.AlzApp.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User findByUsername (String username);
    User findByEmail(String email);


}
