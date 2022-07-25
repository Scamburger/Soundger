package ru.scamburger.Soundger.controllers;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scamburger.Soundger.entity.User;
import ru.scamburger.Soundger.service.AuthTokenService;

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
        authTokenDao.authorize("root","root");
        return "token added";
    }

    @GetMapping("/testfindauth")
    public String findAuthToken(){
        if(!authTokenDao.tryAuthorize("b9908817-b932-4cd4-926a-6c3b1df49d68")){
            return "false account not expired";
        }
        else {
            return "true or exception,account expired";
        }
    }

    @GetMapping("/logout")
    public String logout(){
        authTokenDao.logout("b9908817-b932-4cd4-926a-6c3b1df49d68");
        return "logouted";
    }


}
