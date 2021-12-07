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
	public List<BuildingResponse> buildingSearch(BuildingRequestDto dto) {
		
		return buildingRepository.buildingSearch(dto);
		
		
	/*	List<BuildingResponse> buildingResponses = new ArrayList<>();
		List<BuildingEntity> buildingEntities = buildingRepository.getBuildingSearch(dto);
		
		for (BuildingEntity item : buildingEntities) {
			BuildingResponse buildingResponse = new BuildingResponse();
			buildingResponse.setName(item.getName());
			buildingResponse.setAddress(item.getStreet() + " , " + item.getWard() + " , " + item.getDistrictid());
			buildingResponse.setRentprice(item.getRentprice());
			buildingResponse.setFloorarea(item.getFloorarea());
			buildingResponse.setServicefee(item.getServicefee());
			buildingResponse.setBrokeragefee(item.getBrokeragefee());
			buildingResponses.add(buildingResponse);
			buildingResponses.add(buildingResponse);
		}
		return buildingResponses;
	} 
	*/
}
}