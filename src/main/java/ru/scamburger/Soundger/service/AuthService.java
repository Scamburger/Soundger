package ru.scamburger.Soundger.service;

import org.springframework.web.context.annotation.RequestScope;

import ru.scamburger.Soundger.entity.AuthToken;
import ru.scamburger.Soundger.exception.UnauthorizedException;
import ru.scamburger.Soundger.entity.User;

@RequestScope
public interface AuthService {

    AuthToken authorize(String username, String password) throws UnauthorizedException;

    boolean isAuthorized(String token);

    void logout() throws UnauthorizedException;

    User getCurrentUser();

}
