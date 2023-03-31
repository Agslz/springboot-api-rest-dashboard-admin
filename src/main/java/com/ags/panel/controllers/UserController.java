package com.ags.panel.controllers;

import com.ags.panel.dao.UserDAO;
import com.ags.panel.models.User;
import com.ags.panel.utils.JWTUtil;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        User user = new User();
        user.setId(id);
        user.setName("Lautaro");
        user.setLastname("Addamo");
        user.setEmail("laddamo@gmail.com");
        user.setPhoneNumber("2619462942");
        return user;
    }

    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<User> getUsers(@RequestHeader(value = "Authorization") String token) {

        if(!validateToken(token)){return null;}

        return userDAO.getUsers();
    }

    private boolean validateToken(String token) {
        String IdUser = jwtUtil.getKey(token);
        return IdUser != null;
    }

    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public void registerUsers(@RequestBody User user) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);

        userDAO.register(user);
    }

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)

    public void delete(@PathVariable Long id) {
        userDAO.delete(id);
    }
}
