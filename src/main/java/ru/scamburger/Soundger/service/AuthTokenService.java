package ru.scamburger.Soundger.service;

import ru.scamburger.Soundger.entity.AuthToken;

public interface AuthTokenService {
    AuthToken authorize(String username, String password);
    boolean tryAuthorize(String token);
    void logout(String token);
}
