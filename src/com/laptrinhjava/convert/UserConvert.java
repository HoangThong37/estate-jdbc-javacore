package com.laptrinhjava.convert;

import com.laptrinhjava.dto.response.StaffManagerModel;
import com.laptrinhjava.entity.UserEntity;
import com.laptrinhjava.repository.UserRepository;

public class UserConvert {
    public static StaffManagerModel Convert(UserEntity entity) {
        StaffManagerModel reponse  = new StaffManagerModel();
        reponse.setFullname(entity.getFullname());
        return reponse;
    }
}
