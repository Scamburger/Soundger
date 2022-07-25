package ru.scamburger.Soundger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.scamburger.Soundger.dao.AuthTokenDao;
import ru.scamburger.Soundger.entity.AuthToken;

@Service
public class AuthTokenServiceImpl implements AuthTokenService{

    private final AuthTokenDao authTokenDao;

    public AuthTokenServiceImpl(AuthTokenDao authTokenDao) {
        this.authTokenDao = authTokenDao;
    }

    @Override
    public AuthToken authorize(String username, String password) {
        return authTokenDao.authorize(username,password);
    }

    @Override
    public boolean tryAuthorize(String token) {
        return authTokenDao.tryAuthorize(token);
    }

    @Override
    public void logout(String token) {
        authTokenDao.logout(token);
    }
}
