package com.laptrinhjava.view;

import com.laptrinhjava.controller.UserController;
import com.laptrinhjava.dto.response.UserResponse;
import com.laptrinhjava.entity.UserEntity;

import java.util.List;

public class UserAllView {
    public static void main(String[] args) {
        UserController userController = new UserController();
        List<UserResponse> rs = userController.userSearchAll();
        for (UserResponse userResponse : rs) {
            System.out.println("id : " + userResponse.getId());
            System.out.println("fulName : " + userResponse.getFullname());
            System.out.println("user name : " + userResponse.getUsername());
        }
    }
}
