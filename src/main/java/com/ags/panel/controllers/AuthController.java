package com.ags.panel.controllers;

import com.ags.panel.dao.UserDAO;
import com.ags.panel.models.User;
import com.ags.panel.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) {

        User userLogged = userDAO.obtainUserEmailAndPassword(user);

       if(userLogged != null){

           //We return the token
          return jwtUtil.create(String.valueOf(userLogged.getId()),userLogged.getEmail());

      }
      return "FAIL";
    }

}
