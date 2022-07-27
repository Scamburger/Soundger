package ru.scamburger.Soundger.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.scamburger.Soundger.annotation.Authorized;
import ru.scamburger.Soundger.dto.AuthTokenResponseDto;
import ru.scamburger.Soundger.dto.CurrentUserResponseDto;
import ru.scamburger.Soundger.dto.UserCredentialsRequestDto;
import ru.scamburger.Soundger.entity.AuthToken;
import ru.scamburger.Soundger.entity.User;
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
    public ResponseEntity<AuthTokenResponseDto> login(@RequestBody UserCredentialsRequestDto userCredentialsDto) {
        try {
            AuthToken authToken = authService.authorize(userCredentialsDto.getUsername(),
                    userCredentialsDto.getPassword());
            AuthTokenResponseDto authTokenResponseDto = new AuthTokenResponseDto();
            authTokenResponseDto.setToken(authToken.getToken());
            return new ResponseEntity<>(authTokenResponseDto, HttpStatus.OK);
        } catch (UnauthorizedException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } catch (NoResultException ex) {
            ex.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @PostMapping("/logout")
    @Authorized
    public void logout() {
        try {
            authService.logout();
        } catch (NoResultException | UnauthorizedException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/current")
    @Authorized
    public ResponseEntity<CurrentUserResponseDto> current() {
        User currentUser = authService.getCurrentUser();
        return new ResponseEntity<>(new CurrentUserResponseDto(currentUser), HttpStatus.OK);
    }
}