package ru.scamburger.Soundger.dao;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.scamburger.Soundger.entity.AuthToken;
import ru.scamburger.Soundger.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.UUID;

@Repository
public class AuthTokenDaoImpl implements AuthTokenDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public AuthToken authorize(String username, String password) {
        AuthToken authToken = null;
        try {
            User user = (User) entityManager.createQuery("select u from User as u where u.username=:usernameParam and u.password=:passwordParam")
                    .setParameter("usernameParam", username)
                    .setParameter("passwordParam", password)
                    .getSingleResult();
            entityManager.clear();
            if (user.getAuthToken() != null) {
                entityManager.createQuery("delete from AuthToken as a where a.user_id=:userIdParam")
                        .setParameter("userIdParam", user.getAuthToken().getUser_id()).executeUpdate();
            }
            authToken = new AuthToken();
            authToken.setToken(UUID.randomUUID().toString());
            authToken.setExpiredAt(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)));
            user.setAuthToken(authToken);
            entityManager.merge(user);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return authToken;
    }

    @Override
    public boolean tryAuthorize(String token) {
        try {
            AuthToken authToken = (AuthToken) entityManager.createQuery("select a from AuthToken as a where a.token=:tokenParam")
                    .setParameter("tokenParam", token)
                    .getSingleResult();
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
            entityManager.createQuery("delete from AuthToken as a where a.token=:tokenParam")
                    .setParameter("tokenParam", token)
                    .executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
