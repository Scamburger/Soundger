package ru.scamburger.Soundger.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.scamburger.Soundger.entity.AuthToken;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Repository
public class AuthTokenDaoImpl implements AuthTokenDao{

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    @Transactional
    public void removeAuthTokenByUserId(Long user_id) {
        entityManager.clear();
        entityManager.createQuery("delete from AuthToken as a where a.user_id=:userIdParam")
                .setParameter("userIdParam", user_id).executeUpdate();
    }

    @Override
    public AuthToken getByToken(String token) {
        return (AuthToken) entityManager.createQuery("select a from AuthToken as a where a.token=:tokenParam")
                .setParameter("tokenParam", token)
                .getSingleResult();
    }

    @Override
    public void removeAuthTokenByToken(String token) {
        entityManager.createQuery("delete from AuthToken as a where a.token=:tokenParam")
                .setParameter("tokenParam", token)
                .executeUpdate();
    }
}
