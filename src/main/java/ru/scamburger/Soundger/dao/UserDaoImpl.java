package ru.scamburger.Soundger.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.scamburger.Soundger.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserByUsername(String username) {
        return (User)entityManager.createQuery("select u from User as u where u.username = :usernameParam")
                .setParameter("usernameParam",username)
                .getSingleResult();
    }

    @Override
    @Transactional
    public void saveUser(User user){
        entityManager.merge(user);
    }
}
