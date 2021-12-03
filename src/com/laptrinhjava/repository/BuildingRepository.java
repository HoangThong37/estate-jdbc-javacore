package com.laptrinhjava.repository;

import java.util.List;

import com.laptrinhjava.dto.request.BuildingRequestDto;
import com.laptrinhjava.entity.BuildingEntity;


public interface BuildingRepository { 
	List<BuildingEntity> getBuilding(BuildingRequestDto dto);
	
	List<BuildingEntity> getBuildingSearch(BuildingRequestDto dto);
	

}
