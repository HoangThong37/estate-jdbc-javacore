package com.laptrinhjava.service.impl;

import com.laptrinhjava.convert.UserConvert;
import com.laptrinhjava.dto.response.UserResponse;
import com.laptrinhjava.entity.UserEntity;
import com.laptrinhjava.repository.UserRepository;
import com.laptrinhjava.repository.impl.UserRepositoryImpl;
import com.laptrinhjava.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserRepository userRepository = new UserRepositoryImpl();


    @Override
    public List<UserResponse> findByIdUser(Integer id) {
        List<UserEntity> userEntities = userRepository.findById(id);
        List<UserResponse> userResponses = new ArrayList<>();
        for (UserEntity entity : userEntities) {
            UserResponse model = UserConvert.Convert(entity);
            userResponses.add(model);
        }
        return userResponses;
    }

    @Override
    public List<UserResponse> findAllUser() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for (UserEntity entity : userEntities) {
            UserResponse model = UserConvert.Convert(entity);
            userResponses.add(model);
        }
        return userResponses;
    }
}
