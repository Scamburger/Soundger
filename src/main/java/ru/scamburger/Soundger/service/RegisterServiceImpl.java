package ru.scamburger.Soundger.service;

import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.stereotype.Service;
import ru.scamburger.Soundger.dao.AuthTokenDao;
import ru.scamburger.Soundger.entity.AuthToken;
import ru.scamburger.Soundger.entity.User;

import java.util.Date;
import java.util.UUID;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final int tokenLifetimeInMilliseconds = 1000 * 60 * 60 * 24;
    private final PasswordEncryptor passwordEncryptor;
    private final AuthTokenDao authTokenDao;

    public RegisterServiceImpl(AuthTokenDao authTokenDao, PasswordEncryptor passwordEncryptor) {
        this.authTokenDao = authTokenDao;
        this.passwordEncryptor = passwordEncryptor;
    }

    @Override
    public boolean doRegister(String username, String password) {
        AuthToken authToken = new AuthToken();
        authToken.setToken(UUID.randomUUID().toString());
        authToken.setExpiredAt(new Date(new Date().getTime() + tokenLifetimeInMilliseconds));
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncryptor.encryptPassword(password));
        authToken.setUser(user);
        try {
            authTokenDao.saveAuthToken(authToken);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }
}
