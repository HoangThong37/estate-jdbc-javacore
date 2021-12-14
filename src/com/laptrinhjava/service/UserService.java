package com.laptrinhjava.service;

import com.laptrinhjava.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> findByIdUser(Integer id);
    List<UserResponse> findAllUser();

}
