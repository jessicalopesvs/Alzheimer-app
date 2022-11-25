package com.nci.webapp.AlzApp.service;

import com.nci.webapp.AlzApp.dto.RequestUser;
import com.nci.webapp.AlzApp.model.Role;
import com.nci.webapp.AlzApp.model.User;
import com.nci.webapp.AlzApp.repository.RoleRepository;
import com.nci.webapp.AlzApp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RequestUser requestUser) {
        User user = new User();
        user.setFName(requestUser.getFName());
        user.setLName(requestUser.getLName());
        user.setEmail(requestUser.getEmail());

        //encript password
        user.setPassword(passwordEncoder.encode(requestUser.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);

    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //Create and save role
    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    @Override
    public List<RequestUser> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }
    private RequestUser convertEntityToDto(User user){
        RequestUser requestUser = new RequestUser();
        requestUser.setEmail(user.getEmail());
        return requestUser;
    }

    private RequestUser mapToUserDto(User user){
        RequestUser u = new RequestUser();
        u.setFName(user.getFName());
        u.setLName(user.getLName());
        u.setEmail(user.getEmail());
        return u;
    }

}
