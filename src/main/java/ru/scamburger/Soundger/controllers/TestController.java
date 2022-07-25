package ru.scamburger.Soundger.controllers;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scamburger.Soundger.exception.UnauthorizedException;
import ru.scamburger.Soundger.service.AuthTokenService;
import ru.scamburger.Soundger.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class TestController {

    @PersistenceContext
    private EntityManager entityManager;
    private final AuthTokenService authTokenDao;

    public TestController(AuthTokenService authTokenDao) {
        this.authTokenDao = authTokenDao;
    }

    //Контролер для теста удаления authToken
    @GetMapping("/deleteUser")
    @Transactional
    public User deleteUser() {
        entityManager.createQuery("delete from AuthToken where user_id=2").executeUpdate();
        return new User();
    }

    //Контролер для теста создания Юзера
    @GetMapping("/addUser")
    @Transactional
    public String addUser() {
        User user=new User();
        user.setUsername("root");
        user.setPassword("root");
        entityManager.merge(user);
        return "I hate niggers and ukraine!";
    }

    @GetMapping("/addauthtoken")
    public String testAuth(){
        try {
            authTokenDao.authorize("root","root");
        } catch (UnauthorizedException e) {
           e.getStackTrace();
           return "Unauthorized";
        }
        return "token added";
    }

    @GetMapping("/testfindauth")
    public String findAuthToken(){
        if(!authTokenDao.tryAuthorize("125d4280-f059-4167-904a-247386c233ee")){
            return "false account not expired";
        }
        else {
            return "true or exception,account expired";
        }
    }

    @GetMapping("/logout")
    public String logout(){
        authTokenDao.logout("125d4280-f059-4167-904a-247386c233ee");
        return "logouted";
    }


}
