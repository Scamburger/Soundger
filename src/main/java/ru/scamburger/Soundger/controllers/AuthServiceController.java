package ru.scamburger.Soundger.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.scamburger.Soundger.annotation.Authorized;
import ru.scamburger.Soundger.dto.AuthTokenResponseDto;
import ru.scamburger.Soundger.dto.UserCredentialsDto;
import ru.scamburger.Soundger.entity.AuthToken;
import ru.scamburger.Soundger.exception.UnauthorizedException;
import ru.scamburger.Soundger.service.AuthService;

import javax.persistence.NoResultException;


@RestController
@RequestMapping("/api/auth")
public class AuthServiceController {

    private final AuthService authService;

    public AuthServiceController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthTokenResponseDto> login(@RequestBody UserCredentialsDto userCredentialsDto){
        try {
            AuthToken authToken = authService.authorize(userCredentialsDto.getUsername(),userCredentialsDto.getPassword());
            AuthTokenResponseDto authTokenResponseDto=new AuthTokenResponseDto();
            authTokenResponseDto.setToken(authToken.getToken());
            return new ResponseEntity<>(authTokenResponseDto, HttpStatus.OK);
        } catch (UnauthorizedException e) {
            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED,"USER UNAUTHORIZED");
        }
        catch (NoResultException ex){
            ex.printStackTrace();
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"USER NOT FOUND");
        }
    }

    @PostMapping("/logout")
    @Authorized
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token){
        try {
                authService.logout(token);
                return new ResponseEntity<>("logout complete", HttpStatus.OK);
        }
        catch (NoResultException e){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"TOKEN NOT FOUND");
        }
        }

}


