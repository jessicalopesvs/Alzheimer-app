package com.nci.webapp.AlzApp.component;

import com.nci.webapp.AlzApp.dto.RequestUser;
import com.nci.webapp.AlzApp.model.Role;
import com.nci.webapp.AlzApp.model.RolesType;
import com.nci.webapp.AlzApp.repository.RoleRepository;
import com.nci.webapp.AlzApp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppStartupRunner implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;
    private UserService userService;

    public AppStartupRunner(UserService userService) {
        this.userService = userService;
    }

//    method to create a default admin user if the user list is empty

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //creating and setting roles based on the roles type enum
        RolesType.stream().forEach(rolesType -> {
            if (roleRepository.findByName(rolesType.getValue()) == null) {
                Role r = new Role();
                r.setName(rolesType.getValue());
                roleRepository.save(r);
                log.info("role {} successfully created", rolesType.getValue());
            }
        });

        //create a default admin user to access the system
        if (userService.findByEmail("admin@email.com") == null) {
            RequestUser requestUser = new RequestUser("admin", "admin", "admin", "admin", "admin@email.com", true);
            userService.saveUser(requestUser, RolesType.ADMIN);
            log.info("admin successfully created");
        }
    }
}
