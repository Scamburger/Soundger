package ru.scamburger.Soundger.service;

import ru.scamburger.Soundger.entity.AuthToken;
import ru.scamburger.Soundger.exception.UnauthorizedException;

public interface AuthService {
    AuthToken authorize(String username, String password) throws UnauthorizedException;

    boolean isAuthorized(String token);

    void logout(String token);
}
