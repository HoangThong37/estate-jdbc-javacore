package com.laptrinhjava.controller;

import com.laptrinhjava.dto.response.AssignmentBuildingModel;
import com.laptrinhjava.dto.response.StaffManagerModel;
import com.laptrinhjava.service.AssigmentBuildingService;
import com.laptrinhjava.service.impl.AssigmentBuildingServiceImpl;

import java.util.List;

public class AssignmentBuildingController {
    private AssigmentBuildingService assigmentBuildingService = new AssigmentBuildingServiceImpl();

    public List<StaffManagerModel> findStaffManagerByBuildingId(Long id) {
        return assigmentBuildingService.findStaffManageByBuildingId(id);
    }

    public void assignBuildingToStaff(AssignmentBuildingModel input) {
        assigmentBuildingService.AssignmentBuilding(input);

    }

    }
