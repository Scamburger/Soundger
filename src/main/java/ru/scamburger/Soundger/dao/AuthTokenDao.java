package ru.scamburger.Soundger.dao;

import ru.scamburger.Soundger.entity.AuthToken;

public interface AuthTokenDao {
    AuthToken authorize(String username, String password);

    boolean tryAuthorize(String token);

    void logout(String token);
}
