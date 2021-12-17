package com.laptrinhjava.dto.response;

import java.util.List;

public class AssignmentBuildingModel {
    Long buildingIds;
    List<Long> staffIds;

    public AssignmentBuildingModel(Long buildingIds, List<Long> staffIds) {
        this.buildingIds = buildingIds;
        this.staffIds = staffIds;
    }

    public Long getBuildingIds() {
        return buildingIds;
    }

    public void setBuildingIds(Long buildingIds) {
        this.buildingIds = buildingIds;
    }

    public List<Long> getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(List<Long> staffIds) {
        this.staffIds = staffIds;
    }
}
