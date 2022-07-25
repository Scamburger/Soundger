package ru.scamburger.Soundger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.scamburger.Soundger.dao.AuthTokenDao;
import ru.scamburger.Soundger.dao.UserDao;
import ru.scamburger.Soundger.entity.AuthToken;
import ru.scamburger.Soundger.entity.User;
import ru.scamburger.Soundger.exception.UnauthorizedException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.UUID;

@Service
public class AuthTokenServiceImpl implements AuthTokenService {

    private final UserDao userDao;
    private final AuthTokenDao authTokenDao;

    public AuthTokenServiceImpl(UserDao userDao, AuthTokenDao authTokenDao) {
        this.userDao = userDao;
        this.authTokenDao = authTokenDao;
    }

    @Override
    public AuthToken authorize(String username, String password) throws UnauthorizedException {
        AuthToken authToken = null;
        try {
            User user = userDao.getUserByUsername(username);
            if(!user.getPassword().equals(password)){
                throw new UnauthorizedException();
            }
            if (user.getAuthToken() != null) {
               authTokenDao.removeAuthTokenByUserId(user.getId());
            }
            authToken = new AuthToken();
            authToken.setToken(UUID.randomUUID().toString());
            authToken.setExpiredAt(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)));
            user.setAuthToken(authToken);
            userDao.saveUser(user);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return authToken;
    }

    @Override
    public boolean tryAuthorize(String token) {
        try {
            AuthToken authToken = authTokenDao.getByToken(token);
            if (authToken.getExpiredAt().after(new Date())) {
                return false;
            }
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    @Transactional
    public void logout(String token) {
        try {
            authTokenDao.removeAuthTokenByToken(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
