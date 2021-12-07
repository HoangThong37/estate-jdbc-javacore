package com.laptrinhjava.service;

import java.util.List;

import com.laptrinhjava.dto.request.BuildingRequestDto;
import com.laptrinhjava.dto.response.BuildingResponse;
import com.laptrinhjava.entity.BuildingEntity;



public interface BuildingService {
	
	List<BuildingResponse> buildingSearch(BuildingRequestDto dto);
	
}
