package com.laptrinhjava.service;

import java.util.List;

public interface UserService {
    List<UserResponse> findByIdUser(Integer id);
    List<UserResponse> findAllUser();

}
