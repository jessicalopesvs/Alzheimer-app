package com.nci.webapp.AlzApp.repository;

import com.nci.webapp.AlzApp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String role);
}
