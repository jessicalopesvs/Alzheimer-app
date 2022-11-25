package com.nci.webapp.AlzApp.repository;

import com.nci.webapp.AlzApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User findByUsername (String username);
    User findByEmail(String email);
}
