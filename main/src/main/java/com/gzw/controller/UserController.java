package com.gzw.controller;

import com.alibaba.fastjson.JSON;
import com.gzw.domain.User;
import com.gzw.service.serviceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by gujian on 2017/6/23.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = {"/do_login"})
    public String login(User user, HttpSession session){

        return JSON.toJSONString(userService.loginIn(user,session));

    }

    @PostMapping(value = "/do_register")
    public String register(User user){

        return JSON.toJSONString(userService.register(user));

    }

    @PostMapping(value = "/do_update")
    public String update(User user, HttpSession session){
        User uu = (User) session.getAttribute("user");
        user.setUsername(uu.getUsername());
        return JSON.toJSONString(userService.update(user));

    }
}
