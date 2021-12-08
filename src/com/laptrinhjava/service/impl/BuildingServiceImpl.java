package com.laptrinhjava.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.laptrinhjava.dto.request.BuildingRequestDto;
import com.laptrinhjava.dto.response.BuildingResponse;
import com.laptrinhjava.entity.BuildingEntity;
import com.laptrinhjava.repository.BuildingRepository;
import com.laptrinhjava.repository.impl.BuildingRepositoryImpl;
import com.laptrinhjava.service.BuildingService;
import com.laptrinhjava.utils.DataUtils;

import com.mysql.cj.exceptions.RSAException;

public class BuildingServiceImpl implements BuildingService {
	BuildingRepository buildingRepository = new BuildingRepositoryImpl();

	@Override
	public List<BuildingResponse> buildingSearch(Map<String, Object> params, List<String> types) {
		List<BuildingEntity> buildingEntities = buildingRepository.buildingSearch(params, types);
		return null;
		
}
}