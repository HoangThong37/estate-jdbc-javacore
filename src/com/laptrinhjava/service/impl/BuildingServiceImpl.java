package com.laptrinhjava.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.laptrinhjava.convert.BuildingConvert;
import com.laptrinhjava.dto.request.BuildingRequestDto;
import com.laptrinhjava.dto.response.BuildingResponse;
import com.laptrinhjava.entity.BuildingEntity;
import com.laptrinhjava.entity.DistrictEntity;
import com.laptrinhjava.repository.BuildingRepository;
import com.laptrinhjava.repository.DistrictRepository;
import com.laptrinhjava.repository.impl.BuildingRepositoryImpl;
import com.laptrinhjava.repository.impl.DistrictRepositoryImpl;
import com.laptrinhjava.service.BuildingService;
import com.laptrinhjava.utils.DataUtils;
import com.laptrinhjava.utils.ValidateUtils;
import com.mysql.cj.exceptions.RSAException;

public class BuildingServiceImpl implements BuildingService {
	BuildingRepository buildingRepository = new BuildingRepositoryImpl();

	@Override
	public List<BuildingResponse> buildingSearch(Map<String, Object> params, List<String> types) {
		Map<String, Object> validParams = validateParams(params);	
		// convert entity -> reponse
		List<BuildingEntity> buildingEntities = buildingRepository.buildingSearch(params, types);
		List<BuildingResponse> buildingResponses = new ArrayList<>();
		for(BuildingEntity entity : buildingEntities) {
			BuildingResponse model = BuildingConvert.ConvertBuilding(entity);
			buildingResponses.add(model);
		}
		return buildingResponses;
}

	private Map<String, Object> validateParams(Map<String, Object> params) {
		Map<String, Object> validParams = new HashMap<>();
		for(Map.Entry<String, Object> entry : params.entrySet()) {  // entrySet : được sử dụng để trả lại đối tượng Set có chứa tất cả các keys và values. 
			if (ValidateUtils.isValid(entry.getValue())) {
				validParams.put(entry.getKey().toLowerCase(), entry.getValue());
			}			
		}
		return validParams;
	}
}