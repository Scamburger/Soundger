package ru.scamburger.Soundger.service;

import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.scamburger.Soundger.dao.AuthTokenDao;
import ru.scamburger.Soundger.dao.UserDao;
import ru.scamburger.Soundger.entity.AuthToken;
import ru.scamburger.Soundger.entity.User;
import ru.scamburger.Soundger.exception.UnauthorizedException;

import java.util.Date;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDao userDao;
    private final AuthTokenDao authTokenDao;

    private final int tokenLifetimeInMilliseconds = 1000 * 60 * 60 * 24;

    private User authorizedUser;
    private final PasswordEncryptor passwordEncryptor;

    public AuthServiceImpl(UserDao userDao, AuthTokenDao authTokenDao, PasswordEncryptor passwordEncryptor) {
        this.userDao = userDao;
        this.authTokenDao = authTokenDao;
        this.passwordEncryptor = passwordEncryptor;
    }

    @Override
    public AuthToken authorize(String username, String password) throws UnauthorizedException {
        AuthToken authToken = null;
        User user = userDao.getUserByUsername(username);
        if (!passwordEncryptor.checkPassword(password, user.getPassword())) {
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
        authorizedUser = user;
        return authToken;
    }

    @Override
    public boolean isAuthorized(String token) {
        AuthToken authToken = authTokenDao.getByToken(token);
        if (authToken.getExpiredAt().after(new Date())) {
            authorizedUser = authToken.getUser();
            return true;
        }

        // todo: throw UnauthorizedExpcetion instead of boolean as well as others
        // methods do in this class?
        return false;
    }

    @Override
    @Transactional
    public void logout() throws UnauthorizedException {
        if (authorizedUser == null) {
            throw new UnauthorizedException();
        }
        authTokenDao.removeAuthTokenByToken(authorizedUser.getAuthToken().getToken());
    }

    @Override
    public User getCurrentUser() {
        return authorizedUser;
    }


}
