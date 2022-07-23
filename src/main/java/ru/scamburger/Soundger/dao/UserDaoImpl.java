package ru.scamburger.Soundger.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public UserDetails getUserByUsername(String username) {
        return (UserDetails) entityManager.createQuery("select u from User as u where u.username = :usernameParam").setParameter("usernameParam",username).getSingleResult();
    }
}
