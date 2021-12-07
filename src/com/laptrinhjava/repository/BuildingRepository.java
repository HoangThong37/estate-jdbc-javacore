package com.laptrinhjava.repository;

import java.util.List;

import com.laptrinhjava.dto.request.BuildingRequestDto;
import com.laptrinhjava.dto.response.BuildingResponse;
import com.laptrinhjava.entity.BuildingEntity;


public interface BuildingRepository { 
	List<BuildingResponse> buildingSearch(BuildingRequestDto dto);

	
	
//	List<BuildingEntity> getBuildingSearch(BuildingRequestDto dto);
	

}
