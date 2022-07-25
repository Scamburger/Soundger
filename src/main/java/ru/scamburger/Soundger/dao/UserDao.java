package ru.scamburger.Soundger.dao;


import ru.scamburger.Soundger.entity.User;

public interface UserDao {
    User getUserByUsername(String username);

    void saveUser(User user);
}
