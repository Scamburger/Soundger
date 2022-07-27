package ru.scamburger.Soundger.dao;

import ru.scamburger.Soundger.entity.AuthToken;

public interface AuthTokenDao {

    AuthToken getByToken(String token);

    void removeAuthTokenByToken(String token);

    void saveAuthToken(AuthToken authToken);
    
}
