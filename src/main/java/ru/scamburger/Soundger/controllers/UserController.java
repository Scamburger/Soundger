package ru.scamburger.Soundger.controllers;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scamburger.Soundger.entity.Role;
import ru.scamburger.Soundger.entity.User;
import ru.scamburger.Soundger.entity.AuthToken;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.Date;

@RestController
public class UserController {

    @PersistenceContext
    private EntityManager entityManager;

    //Контролер для теста создания Юзера, назначения роли и присвоения AuthToken'a
    @GetMapping("/random")
    @Transactional
    public User addUser() {
        User user=new User();
        Role role=new Role();
        AuthToken authToken=new AuthToken();
        authToken.setToken("random token");
        authToken.setExpiredAt(new Date());
        role.setRoleName("ROLE_ADMIN");
        user.setUsername("root");
        user.setPassword("root");
        user.setRoles(Collections.singleton(role));
        user.setAuthToken(authToken);
        entityManager.merge(user);
        return user;
    }

    @GetMapping("/test")
    public String testController() {
        return "I hate niggers and ukraine!";
    }
}
