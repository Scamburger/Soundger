package ru.scamburger.Soundger.dao;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDao {
    UserDetails getUserByUsername(String username);
}
