package ru.scamburger.Soundger.dao;

import ru.scamburger.Soundger.entity.AuthToken;

public interface AuthTokenDao {

    void removeAuthTokenByUserId(Long user_id);

    AuthToken getByToken(String token);

    void removeAuthTokenByToken(String token);
}
