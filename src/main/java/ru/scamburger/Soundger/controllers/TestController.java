package ru.scamburger.Soundger.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.scamburger.Soundger.dto.AuthTokenResponseDto;
import ru.scamburger.Soundger.dto.UserCredentialsRequestDto;
import ru.scamburger.Soundger.exception.UnauthorizedException;
import ru.scamburger.Soundger.service.AuthService;
import ru.scamburger.Soundger.annotation.Authorized;
import ru.scamburger.Soundger.entity.User;
import ru.scamburger.Soundger.service.RegisterService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class TestController {

    @PersistenceContext
    private EntityManager entityManager;
    private final AuthService authService;

    public TestController(AuthService authService) {
        this.authService = authService;
    }

    //Контролер для теста удаления authToken
    @GetMapping("/deleteUser")
    @Transactional
    public User deleteUser() {
        entityManager.createQuery("delete from AuthToken where user_id=2").executeUpdate();
        return new User();
    }

    @GetMapping("/addauthtoken")
    public String testAuth() {
        try {
            authService.authorize("root", "root");
        } catch (UnauthorizedException e) {
            e.getStackTrace();
            return "Unauthorized";
        }
        return "token added";
    }

    @GetMapping("/testfindauth")
    public String findAuthToken() {
        if (!authService.isAuthorized("342af481-e953-44c3-974f-8151717b06c1")) {
            return "false account not expired";
        } else {
            return "true or exception,account expired";
        }
    }

    @GetMapping("/logout")
    @Authorized
    public String logout() {
        try {
            authService.logout();
        } catch (UnauthorizedException e) {
            return "error occupied";
        }
        return "logouted";
    }

}
