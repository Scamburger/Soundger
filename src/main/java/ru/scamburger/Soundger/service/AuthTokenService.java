package ru.scamburger.Soundger.service;

import ru.scamburger.Soundger.entity.AuthToken;
import ru.scamburger.Soundger.exception.UnauthorizedException;

public interface AuthTokenService {
    AuthToken authorize(String username, String password) throws UnauthorizedException;

    boolean tryAuthorize(String token);

    void logout(String token);
}
