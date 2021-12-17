package com.laptrinhjava.controller;

import com.laptrinhjava.service.UserService;
import com.laptrinhjava.service.impl.UserServiceImpl;

import java.util.List;

public class UserController {

    private  UserService service = new UserServiceImpl();

    public List<UserResponse> userSearchById(Integer id) {

        return service.findByIdUser(id);
    }
    public List<UserResponse> userSearchAll() {
        return service.findAllUser();
    }
}
