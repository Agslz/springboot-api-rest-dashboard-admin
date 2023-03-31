package com.ags.panel.dao;

import com.ags.panel.models.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsers();

    void delete(Long id);

    void register(User user);

    User obtainUserEmailAndPassword(User user);
}
