package com.laptrinhjava.service;

import com.laptrinhjava.dto.response.AssignmentBuildingModel;
import com.laptrinhjava.dto.response.StaffManagerModel;

import java.sql.SQLException;
import java.util.List;

public interface AssigmentBuildingService {
    void AssignmentBuilding(AssignmentBuildingModel input);
    List<StaffManagerModel> findStaffManageByBuildingId(Long id); // tìm nhân viên quản lí = id tòa nhà
}
