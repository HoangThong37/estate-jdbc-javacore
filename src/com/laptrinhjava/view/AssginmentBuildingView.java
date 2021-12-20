package com.laptrinhjava.view;

import com.laptrinhjava.controller.AssignmentBuildingController;
import com.laptrinhjava.dto.response.AssignmentBuildingModel;
import com.laptrinhjava.dto.response.StaffManagerModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AssginmentBuildingView {
    public static void main(String[] args) {
        Long buildingId = 4L;
        List<Long> staffIds = new ArrayList<>();
        staffIds.add(2L);
        staffIds.add(4L);

        AssignmentBuildingModel input = new AssignmentBuildingModel(buildingId, staffIds);

        AssignmentBuildingController assignmentBuildingController = new AssignmentBuildingController();

        System.out.println("Danh sách nhân viên đang quản lí toà nhà : ");
        List<StaffManagerModel> beforeStaffs = assignmentBuildingController.findStaffManagerByBuildingId(buildingId);
        for (StaffManagerModel item : beforeStaffs) {
            System.out.println("name :" +item.getFullname() +" - check " +item.getCheck());
        }
        assignmentBuildingController.assignBuildingToStaff(input);


        System.out.println("Nhân viên sau khi cập nhật : ");
        List<StaffManagerModel> afterStaffs = assignmentBuildingController.findStaffManagerByBuildingId(buildingId);
        for (StaffManagerModel item : afterStaffs) {
            System.out.println("name :" +item.getFullname() +" - check " +item.getCheck());
        }

//        System.out.println("BEFORE ASSIGNING");
//        System.out.println("LIST STAFFS MANAGE BUILDING");
//        for (
//                StaffManagerModel item : assignmentBuildingController.findStaffManagerByBuildingId(buildingId)) {
//            // print
//        }
//
//        System.out.println("AFTER ASSIGNING");
//
//        assignmentBuildingController.assignBuildingToStaff(input);
//
//        System.out.println("LIST STAFFS MANAGE BUILDING AFTER ASSIGNING");
//        for (
//                StaffManagerModel item : assignmentBuildingController.findStaffManagerByBuildingId(buildingId)) {
//            // print
//        }
    } }
