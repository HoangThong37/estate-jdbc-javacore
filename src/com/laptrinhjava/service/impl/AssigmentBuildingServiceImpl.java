package com.laptrinhjava.service.impl;

import com.laptrinhjava.convert.UserConvert;
import com.laptrinhjava.dto.response.AssignmentBuildingModel;
import com.laptrinhjava.dto.response.StaffManagerModel;
import com.laptrinhjava.entity.UserEntity;
import com.laptrinhjava.repository.AssigmentBuildingRepo;
import com.laptrinhjava.repository.UserRepository;
import com.laptrinhjava.repository.impl.AssigmentBuildingRepoImpl;
import com.laptrinhjava.repository.impl.UserRepositoryImpl;
import com.laptrinhjava.service.AssigmentBuildingService;
import com.laptrinhjava.utils.ValidateUtils;

import java.util.ArrayList;
import java.util.List;

public class AssigmentBuildingServiceImpl implements AssigmentBuildingService {
    private UserRepository userRepository = new UserRepositoryImpl();
    private AssigmentBuildingRepo assigmentBuilding = new AssigmentBuildingRepoImpl();

    @Override
    public List<AssignmentBuildingModel> AssignmentBuilding(AssignmentBuildingModel input) {
        List<UserEntity> currentStaffsCovert = userRepository.findById(input.getBuildingIds());
        List<Long> inputStaffs = check(input);
        List<Long> oldStaffs = new ArrayList<>(); // list nv cũ
        List<Long> newStaffs = new ArrayList<>();  //  list nv mới
        List<Long> currentStaffs = new ArrayList<>(); // list nv hiện tại

        for (UserEntity item : currentStaffsCovert) {
            currentStaffs.add(item.getId());
        }
//        CheckAssignmentBuilding(currentStaffs,inputStaffs,oldStaffs,newStaffs);
        if (inputStaffs.isEmpty()) {
            if (currentStaffs.isEmpty()) {
                return null;
            } else {
                oldStaffs.addAll(currentStaffs);
            }
        }
        else {
            if (!currentStaffs.isEmpty()) {
                for (int i = 0; i < currentStaffs.size(); i++) {
                    if (!(inputStaffs.contains(currentStaffs.get(i)))) {
                        oldStaffs.add(currentStaffs.get(i));
                    } else {
                        inputStaffs.remove(currentStaffs.get(i));
                    }}
            }
            if (inputStaffs.isEmpty()) {
                return null;
            } else {
                newStaffs.addAll(inputStaffs);
            }}

        assigmentBuilding.ASS(input.getBuildingIds(), newStaffs, oldStaffs); //
        return new ArrayList<>();
    }

    private List<Long> check(AssignmentBuildingModel input) {
        List<UserEntity> userEntityList = userRepository.findAll();
        List<Long> userAll = new ArrayList<>();
        List<Long> userInput = new ArrayList<>();
        for (UserEntity userEntity : userEntityList) {
            userAll.add(userEntity.getId());
        }
        for (Long item : input.getStaffIds()) {
            userInput.add(item);
        }
        return userInput;
    }

    @Override
    public List<StaffManagerModel> findStaffManageByBuildingId(Long id) { // list Nhân viên Quản lý theo Id Tòa nhà
        List<UserEntity> users = userRepository.findAll(); // find all
        List<UserEntity> assignmentStaffs = userRepository.findById(id); // tìm theo id

        List<StaffManagerModel> rs = new ArrayList<>();
        List<Long> list = new ArrayList<>();
        for (UserEntity entity : assignmentStaffs) {
            list.add(entity.getId());
        }
        for (UserEntity item : users) {
            StaffManagerModel staffManagerModel = UserConvert.Convert(item);
            if (list.contains(item.getId())) {
                staffManagerModel.setCheck("Checked"); //
            }
            rs.add(staffManagerModel);
        }
        return rs;
    }
}
