package com.laptrinhjava.repository;

import com.laptrinhjava.entity.UserEntity;

import java.util.List;

public interface UserRepository {
    List<UserEntity> findById(Long id);
    List<UserEntity> findAll();
//    List<UserEntity> GiaoToaNha(Long[] id);
}
