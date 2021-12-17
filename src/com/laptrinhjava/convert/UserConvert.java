package com.laptrinhjava.convert;

import com.laptrinhjava.entity.UserEntity;

public class UserConvert {
    public static UserResponse Convert(UserEntity entity) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(entity.getId());
        userResponse.setFullname(entity.getFullname());
        userResponse.setUsername(entity.getUsername());

        return userResponse;
    }
}
