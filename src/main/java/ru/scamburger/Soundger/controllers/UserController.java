package ru.scamburger.Soundger.controllers;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scamburger.Soundger.entity.User;
import ru.scamburger.Soundger.entity.AuthToken;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@RestController
public class UserController {

    @PersistenceContext
    private EntityManager entityManager;

    //Контролер для теста удаления authToken
    @GetMapping("/deleteUser")
    @Transactional
    public User deleteUser() {
        entityManager.createQuery("delete from AuthToken where user_id=1").executeUpdate();
        return new User();
    }

    //Контролер для теста создания Юзера
    @GetMapping("/addUser")
    @Transactional
    public String addUser() {
        User user=new User();
        AuthToken authToken=new AuthToken();
        authToken.setToken("random token");
        authToken.setExpiredAt(new Date());
        user.setUsername("root");
        user.setPassword("root");
        user.setAuthToken(authToken);
        entityManager.merge(user);
        return "I hate niggers and ukraine!";
    }
}
