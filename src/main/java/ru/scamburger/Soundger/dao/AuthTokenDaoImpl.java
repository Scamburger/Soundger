package ru.scamburger.Soundger.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.scamburger.Soundger.entity.AuthToken;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
@Repository
public class AuthTokenDaoImpl implements AuthTokenDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public AuthToken getByToken(String token) throws NoResultException{
            return (AuthToken) entityManager.createQuery("select a from AuthToken as a where a.token=:tokenParam")
                    .setParameter("tokenParam", token)
                    .getSingleResult();
    }

    @Override
    @Transactional
    public void removeAuthTokenByToken(String token) {
        entityManager.clear();
        entityManager.createQuery("delete from AuthToken as a where a.token=:tokenParam")
                .setParameter("tokenParam", token)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void saveAuthToken(AuthToken authToken) {
        entityManager.merge(authToken);
    }
}
