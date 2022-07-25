package ru.scamburger.Soundger.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.scamburger.Soundger.dao.AuthTokenDao;
import ru.scamburger.Soundger.dao.UserDao;
import ru.scamburger.Soundger.entity.AuthToken;
import ru.scamburger.Soundger.entity.User;
import ru.scamburger.Soundger.exception.UnauthorizedException;

import javax.persistence.NoResultException;
import java.util.Date;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDao userDao;
    private final AuthTokenDao authTokenDao;

    private final int tokenLifetimeInMilliseconds = 1000 * 60 * 60 * 24;

    public AuthServiceImpl(UserDao userDao, AuthTokenDao authTokenDao) {
        this.userDao = userDao;
        this.authTokenDao = authTokenDao;
    }

    @Override
    public AuthToken authorize(String username, String password) throws UnauthorizedException {
        AuthToken authToken = null;
        User user = userDao.getUserByUsername(username);
        if (!user.getPassword().equals(password)) {
            throw new UnauthorizedException();
        }
        if (user.getAuthToken() != null) {
            authTokenDao.removeAuthTokenByToken(user.getAuthToken().getToken());
            user.setAuthToken(null);
        }
        authToken = new AuthToken();
        authToken.setToken(UUID.randomUUID().toString());
        authToken.setExpiredAt(new Date(new Date().getTime() + tokenLifetimeInMilliseconds));
        authToken.setUser(user);
        authTokenDao.saveAuthToken(authToken);
        return authToken;
    }

    @Override
    public boolean isAuthorized(String token) {
        try {
            AuthToken authToken = authTokenDao.getByToken(token);
            if (authToken.getExpiredAt().after(new Date())) {
                return false;
            }
        } catch (NoResultException e) {
            throw e;
        }
        return true;
    }

    @Override
    @Transactional
    public void logout(String token) {
            authTokenDao.removeAuthTokenByToken(token);
    }
}
