package com.laptrinhjava.controller;

import java.util.List;
import java.util.Map;

import com.laptrinhjava.dto.request.BuildingRequestDto;
import com.laptrinhjava.dto.response.BuildingResponse;
import com.laptrinhjava.service.BuildingService;
import com.laptrinhjava.service.impl.BuildingServiceImpl;

public class BuldingController {
	private BuildingService buildingService = new BuildingServiceImpl();

	public List<BuildingResponse> getBuildingSearch(Map<String, Object> params, List<String> types) {
		// covert từ tk 
		return buildingService.buildingSearch(params, types);
	}
}
