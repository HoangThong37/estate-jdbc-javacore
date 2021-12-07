package com.laptrinhjava.controller;

import java.util.List;

import com.laptrinhjava.dto.request.BuildingRequestDto;
import com.laptrinhjava.dto.response.BuildingResponse;
import com.laptrinhjava.service.BuildingService;
import com.laptrinhjava.service.impl.BuildingServiceImpl;

public class BuldingController {
	private BuildingService buildingService = new BuildingServiceImpl();
//	public List<BuildingResponse> getBuilding(BuildingRequestDto dto) {
//		// covert từ tk 
//		return buildingService .buildingSearchAll(dto);
//	}
//	
	public List<BuildingResponse> getBuildingSearch(BuildingRequestDto dto) {
		// covert từ tk 
		return buildingService .buildingSearch(dto);
	}
}
