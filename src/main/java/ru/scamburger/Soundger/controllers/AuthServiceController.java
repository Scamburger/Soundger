package ru.scamburger.Soundger.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.scamburger.Soundger.dto.UserCredentialsDto;
import ru.scamburger.Soundger.entity.AuthToken;
import ru.scamburger.Soundger.exception.UnauthorizedException;
import ru.scamburger.Soundger.service.AuthService;

import javax.persistence.NoResultException;

@RestController
@RequestMapping("/api")
public class AuthServiceController {

    private final AuthService authService;

    public AuthServiceController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody UserCredentialsDto userCredentialsDto){
        try {
            AuthToken authToken = authService.authorize(userCredentialsDto.getUsername(),userCredentialsDto.getPassword());
            return new ResponseEntity<>(authToken.getToken(), HttpStatus.OK);
        } catch (UnauthorizedException e) {
            e.printStackTrace();
            return new ResponseEntity<>("unauthorized",HttpStatus.UNAUTHORIZED);
        }
        catch (NoResultException ex){
            ex.printStackTrace();
            return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/auth/logout")
    public ResponseEntity<String> logout(@RequestParam(name = "token") String token){
        try {
            if (!authService.isAuthorized(token)) {
                authService.logout(token);
                return new ResponseEntity<>("logout complete", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("token expired at", HttpStatus.NOT_FOUND);
            }
        }
        catch (NoResultException e){
            return new ResponseEntity<>("token not found",HttpStatus.NOT_FOUND);
        }
        }
    }

