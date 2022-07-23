package ru.scamburger.Soundger.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scamburger.Soundger.entity.Role;
import ru.scamburger.Soundger.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;

@RestController
public class UserController {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/random")
    @Transactional
    public User addUser(){
//        User user=new User();
//        Role role=new Role();
//        role.setRoleName("ROLE_USER");
//        user.setUsername("root");
//        user.setPassword(passwordEncoder.encode("root"));
//        user.setRoles(Collections.singleton(role));
//        entityManager.merge(user);
//        return user;
        System.out.println(passwordEncoder.encode("user"));
        System.out.println(passwordEncoder.encode("admin"));
        return new User();
    }
}
